package com.robotics.nodechat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Ack;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;

public class Chat extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Adapter_Chat mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    static Context context = null;
    private EditText mInputMessageView;
    Button btnSend;
    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            Log.d("YOGE","Call Massage  "+args[0].toString());
            ((Activity)context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject data = (JSONObject) args[0];
                        String username = data.getString("username");
                        String message = data.getString("text");
                        String dateString = DateFormat.format("MM/dd/yyyy hh:mm:ss", new Date(data.getLong("createdAt"))).toString();
                        addMessage(username, message,dateString);
                    } catch (JSONException e) {
                        return;
                    }
                }
            });
        }
    };

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("https://hututuchat.herokuapp.com");
        } catch (URISyntaxException e) {}
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        context = this;
        btnSend = findViewById(R.id.btn_send);
        mInputMessageView = findViewById(R.id.messageview);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("YOGE","Massge");
                attemptSend();
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.gift_recyclerview);
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new Adapter_Chat(new ArrayList<HTT_MSG>());
        mRecyclerView.setAdapter(mAdapter);
        mSocket.connect();
        mSocket.on("message", onNewMessage);
        JSONObject username = new JSONObject();
        try {
            username.put("username",MainActivity.username);
            username.put("room",MainActivity.room);
        }catch (Exception e){}
        mSocket.emit("join",username,new Ack() {
            @Override
            public void call(Object... args) {
                if(args.length > 0) {
                    Log.d("YOGE", "args -> " + args[0].toString());
                    MainActivity.username_not_available = args[0].toString();
                    finish();
                }
                //TODO process ACK
            }
        });
    }

    private void attemptSend() {
        String message = mInputMessageView.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            return;
        }

        mInputMessageView.setText("");
        mSocket.emit("sendMessage",message,new Ack() {
            @Override
            public void call(Object... args) {
                Log.d("YOGE","args -> "+args.length);
                //TODO process ACK
            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        mSocket.disconnect();
        mSocket.off("increment", onNewMessage);
    }
    public void addMessage(String username, String message,String creatat){
        mAdapter.addItem(new HTT_MSG(username,message,creatat));
        Log.d("YOGE",message);
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                // Call smooth scroll
                mRecyclerView.smoothScrollToPosition(mAdapter.getItemCount() - 1);
            }
        });
    }
}
