package com.robotics.nodechat;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;


        ;

/**
 * Created by Twist Mobile on 11/29/2016.
 */

public class Adapter_Chat extends RecyclerView.Adapter<Adapter_Chat.ChatViewHolder> {

    private List<HTT_MSG> OutList;

    public Adapter_Chat(List<HTT_MSG> _OutList) {
        this.OutList = _OutList;
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mtxtName,mtxtMsg,mtxtDate;
        private ChatViewHolder(View view) {
            super(view);
            mtxtName = view.findViewById(R.id.name);
            mtxtMsg = view.findViewById(R.id.msg);
            mtxtDate = view.findViewById(R.id.date);
        }
        @Override
        public void onClick(View view) {}
        final protected Context getContext() {
            return itemView.getContext();
        }
    }
    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_chat, parent, false);
        return new ChatViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position){
        holder.mtxtName.setText(OutList.get(position).name);
        holder.mtxtMsg.setText(OutList.get(position).msg);
        holder.mtxtDate.setText(OutList.get(position).date);
    }
    @Override
    public int getItemCount() {
        return OutList.size();
    }
    public void addItems(List<HTT_MSG> _OutList) {
        OutList.addAll(_OutList);
        notifyDataSetChanged();
    }
    public void addItem(HTT_MSG _msg) {
        OutList.add(_msg);
        notifyDataSetChanged();


    }
}