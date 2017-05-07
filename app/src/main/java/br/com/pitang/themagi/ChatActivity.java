package br.com.pitang.themagi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.chat_list)
    RecyclerView chatList;

    @BindView(R.id.user_text_input)
    EditText userTextInput;

    private ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        adapter = new ChatAdapter(Arrays.asList(new ChatMessage("TESTEDAS DAS DAS T TRE",ChatMessage.TYPE.LEFT ), new ChatMessage("654645645 DAS DAS T TRE", ChatMessage.TYPE.LEFT)));

        chatList.setLayoutManager(layoutManager);
        chatList.setAdapter(adapter);
        chatList.setHasFixedSize(true);
    }

    @OnClick(R.id.send_text_input)
    public void onSendButtonClicked(){
        String message = userTextInput.getText().toString();
        ChatMessage chatMessage = new ChatMessage(message, ChatMessage.TYPE.RIGHT);
        adapter.addMessage(chatMessage);

        userTextInput.setText("");
    }
}
