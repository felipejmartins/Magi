package br.com.pitang.themagi;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private List<ChatMessage> messages;

    public ChatAdapter(List<ChatMessage> messages) {
        this.messages = new ArrayList<>(messages);
    }

    public void addMessage(ChatMessage chatMessage){
        this.messages.add(0,chatMessage);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return messages.get(position).getType();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType == ChatMessage.TYPE.LEFT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_left_chat_message, parent, false);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_right_chat_message, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChatMessage chatMessage = messages.get(position);
        holder.messageView.setText(chatMessage.getMessage());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.message_ballon)
        TextView messageView;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
