package instavans.sanchit.instavans;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import instavans.sanchit.instavans.adapter.ChatAdapter;
import instavans.sanchit.instavans.restapi.models.ChatRecieveAPI;
import instavans.sanchit.instavans.restapi.models.ChatSendAPI;
import instavans.sanchit.instavans.utilties.InstavanPrefs;
import instavans.sanchit.instavans.utilties.VerticalSpaceItemDecoration;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by sanchitjain on 06/03/16.
 */
public class chatActivity extends AppCompatActivity{

    @Bind(R.id.chatList)
    RecyclerView chatList;
    @Bind(R.id.chat_text)
    EditText chatText;
    @Bind(R.id.sendi)
    ImageView sendi;
    ChatAdapter chatAdap;
    String jobNUmber;
    InstavanPrefs mPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);
        ButterKnife.bind(this);
        jobNUmber = getIntent().getStringExtra("JOBNUMBER");
        mPrefs = InstavanPrefs.getInstance(this);
        UpdateList();
        sendi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chatText.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Message", Toast.LENGTH_SHORT).show();
                    return;
                }else
                {
                    String msg = mPrefs.getNAME()+" : "+chatText.getText().toString();
                    Call<ChatSendAPI> sendAPICall =  Instavans.getRestClient(chatActivity.this).getApiService()
                            .sendMsg(msg, jobNUmber);
                    sendAPICall.enqueue(new Callback<ChatSendAPI>() {
                        @Override
                        public void onResponse(Response<ChatSendAPI> response, Retrofit retrofit) {
                            if(response.body().getSuccess())
                            {
                                chatText.setText("");
                                UpdateList();
                            }else
                            {
                                Toast.makeText(getApplicationContext(), "Message not sent", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                           Toast.makeText(getApplicationContext(), "Something went Wrong", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
    public void UpdateList()
    {
        Call<ChatRecieveAPI> chatRecieveAPICall =  Instavans.getRestClient(this).getApiService().readSms(jobNUmber);
        chatRecieveAPICall.enqueue(new Callback<ChatRecieveAPI>() {
            @Override
            public void onResponse(Response<ChatRecieveAPI> response, Retrofit retrofit) {
                if(response.body().getSuccess())
                {
                    chatAdap = new ChatAdapter(chatActivity.this,response.body().getResponse());

                    chatList.setAdapter(chatAdap);
                    chatList.setLayoutManager(new LinearLayoutManager(chatActivity.this));
                    chatList.addItemDecoration(new VerticalSpaceItemDecoration(5,3));

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }


}
