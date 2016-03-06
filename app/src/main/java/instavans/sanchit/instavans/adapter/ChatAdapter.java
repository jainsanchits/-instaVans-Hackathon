package instavans.sanchit.instavans.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import instavans.sanchit.instavans.R;
import instavans.sanchit.instavans.datamodel.ChatRecieve;

/**
 * Created by sanchitjain on 06/03/16.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {
    private String tabTitles[];
    private Context context;
    private LayoutInflater inflater;
    private List<ChatRecieve> chatRecieveList = Collections.EMPTY_LIST;

    public ChatAdapter( Context context,List<ChatRecieve> chatRecieveList) {
        this.context = context;
        inflater =  LayoutInflater.from(context);
        this.chatRecieveList = chatRecieveList;

    }

    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.chat_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ChatAdapter.MyViewHolder holder, final int position) {
        ChatRecieve current = chatRecieveList.get(position);
        holder.chatMessage.setText(current.getMessage());
        holder.chatTime.setText(current.getTime());
        holder.chatName.setText(current.getName());
    }

    @Override
    public int getItemCount() {
        return chatRecieveList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.chatMessage)TextView chatMessage;
        @Bind(R.id.chatTime)TextView chatTime;
        @Bind(R.id.chatName)TextView chatName;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
