package instavans.sanchit.instavans.restapi;

import android.content.Context;

import com.squareup.okhttp.Request;

import java.io.IOException;

import instavans.sanchit.instavans.Instavans;
import instavans.sanchit.instavans.restapi.service.APIService;
import okio.Buffer;
import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by sanchitjain on 05/03/16.
 */
public class RestClient {
    private static final String BASE_URL = "http://192.168.6.158:8888/Instavans/public/";
    private APIService apiService;
    private Context context;
    private String TAG = "LOGS";

    public RestClient(Context context) {

        this.context = context;


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(Instavans.getOkHttpClient())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        apiService = retrofit.create(APIService.class);

    }

    public static String bodyToString(Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    public APIService getApiService(){
        return apiService;
    }
}
