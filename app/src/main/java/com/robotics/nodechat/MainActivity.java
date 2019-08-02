package com.robotics.nodechat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static String username = "Shruti";
    static String room = "hello";
    static String link_signup = "hello";
    static String username_not_available = "";
    EditText txtUser,txtRoom;
    TextView txtlink_signup;
    Button btnJoin;
    static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        txtUser = findViewById(R.id.input_email);
        txtRoom = findViewById(R.id.input_password);
        txtlink_signup = findViewById(R.id.link_signup);
        txtlink_signup.setText("");
        btnJoin = findViewById(R.id.btn_login);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username_not_available = "";
                if(txtUser.getText().toString().length() > 0 && txtRoom.getText().toString().length() > 0){
                    username = txtUser.getText().toString();
                    room = txtRoom.getText().toString();
                }
                Intent intent = new Intent(MainActivity.this, Chat.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        txtlink_signup.setText(username_not_available);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
