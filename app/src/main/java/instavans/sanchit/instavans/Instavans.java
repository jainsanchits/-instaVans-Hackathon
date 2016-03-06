package instavans.sanchit.instavans;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import instavans.sanchit.instavans.restapi.RestClient;

/**
 * Created by sanchitjain on 05/03/16.
 */
public class Instavans extends Application  {

    private static RestClient restClient;
    private static OkHttpClient okHttpClient;
    private static OkHttpClient noCookieOkHttpClient;
    private static Context mContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        Parse.initialize(this);
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

    public static RestClient getRestClient(Context context) {
        mContext = context.getApplicationContext();
        if(restClient==null){
            restClient = new RestClient(mContext);
        }
        return restClient;
    }



    public static OkHttpClient getOkHttpClient() {
        if(okHttpClient==null){
            HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
            logger.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient = new OkHttpClient();
            okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);
            okHttpClient.interceptors().add(logger);
        }
        return okHttpClient;
    }

}
