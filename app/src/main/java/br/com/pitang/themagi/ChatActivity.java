package br.com.pitang.themagi;

import android.content.Context;
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
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .client(getOkHttpClient(this))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient getOkHttpClient(Context context) {
        OkHttpClient.Builder okClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel( HttpLoggingInterceptor.Level.BASIC);
        okClientBuilder.addInterceptor(httpLoggingInterceptor);
        return okClientBuilder.build();
    }

    @OnClick(R.id.send_text_input)
    public void onSendButtonClicked(){
        String message = userTextInput.getText().toString();
        ChatMessage chatMessage = new ChatMessage(message, ChatMessage.TYPE.RIGHT);
        adapter.addMessage(chatMessage);

        userTextInput.setText("");
    }
}
