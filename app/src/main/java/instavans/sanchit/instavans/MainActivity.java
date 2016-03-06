package instavans.sanchit.instavans;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.parse.ParsePush;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import instavans.sanchit.instavans.restapi.models.SignupAPI;
import instavans.sanchit.instavans.utilties.InstavanPrefs;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.porterName)
    EditText porterName;
    @Bind(R.id.porterNumber)
    EditText porterNumber;
    @Bind(R.id.porterMobile)
    EditText porterMobile;
    @Bind(R.id.login)
    Button login;
    @Bind(R.id.signup)
    Button signup;
    InstavanPrefs mPrefs;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPrefs = InstavanPrefs.getInstance(this);
        if(mPrefs.getIS_LOGGED_IN()) {
            callMainActivity();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (porterNumber.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Porter Number", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Call<SignupAPI> signupAPICall = Instavans.getRestClient(MainActivity.this).getApiService().userLogin(
                            porterNumber.getText().toString());
                    signupAPICall.enqueue(new Callback<SignupAPI>() {
                        @Override
                        public void onResponse(Response<SignupAPI> response, Retrofit retrofit) {
                            if (response.body().getSuccess()) {
                                mPrefs.setIS_LOGGED_IN(true);
                                mPrefs.setCONTACT(response.body().getResponse().getContact());
                                mPrefs.setNAME(response.body().getResponse().getDisplayName());
                                mPrefs.setPORTERID(response.body().getResponse().getPorterId());
                                mPrefs.setREMEBERTOKEN(response.body().getResponse().getRememberToken());
                                mPrefs.setUSER_ID(String.valueOf(response.body().getResponse().getId()));
                                ParsePush.subscribeInBackground("PORTER_"+mPrefs.getUSER_ID());
                                callMainActivity();
                            } else {
                                Toast.makeText(getApplicationContext(), response.body().getErrors(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {

                        }
                    });
                }
            }
        });
    }
    @OnClick(R.id.signup)
     public void callSignupApi()
    {
        if(porterName.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Please Enter Your Name",Toast.LENGTH_SHORT).show();
            return;
        }else if(porterMobile.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Please Enter Your Mobile Number",Toast.LENGTH_SHORT).show();
            return;
        }else
        {
            Call<SignupAPI> signupAPICall = Instavans.getRestClient(MainActivity.this).getApiService().userSignUp(
                    porterMobile.getText().toString(),porterName.getText().toString());

            signupAPICall.enqueue(new Callback<SignupAPI>() {
                @Override
                public void onResponse(Response<SignupAPI> response, Retrofit retrofit) {
                    if(response.body().getSuccess())
                    {
                        mPrefs.setIS_LOGGED_IN(true);
                        mPrefs.setCONTACT(response.body().getResponse().getContact());
                        mPrefs.setNAME(response.body().getResponse().getDisplayName());
                        mPrefs.setPORTERID(response.body().getResponse().getPorterId());
                        mPrefs.setREMEBERTOKEN(response.body().getResponse().getRememberToken());
                        mPrefs.setUSER_ID(String.valueOf(response.body().getResponse().getId()));
                        Toast.makeText(getApplicationContext(),"response"+response.body().getResponse().getPorterId(),Toast.LENGTH_SHORT).show();
                        ParsePush.subscribeInBackground("PORTER_" + mPrefs.getUSER_ID());
                        callMainActivity();
                    }else
                    {
                        Toast.makeText(getApplicationContext(),response.body().getErrors(),Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });

        }

    }
    public void callMainActivity()
    {
        Intent i =  new Intent();
        i.setClass(MainActivity.this,DashboardActivity.class);
        startActivity(i);
    }


}
