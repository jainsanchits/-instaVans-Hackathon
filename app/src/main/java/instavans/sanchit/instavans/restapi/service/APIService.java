package instavans.sanchit.instavans.restapi.service;

import instavans.sanchit.instavans.datamodel.Signup;
import instavans.sanchit.instavans.restapi.models.ActionAPI;
import instavans.sanchit.instavans.restapi.models.ActionListAPI;
import instavans.sanchit.instavans.restapi.models.ChatRecieveAPI;
import instavans.sanchit.instavans.restapi.models.ChatSendAPI;
import instavans.sanchit.instavans.restapi.models.FindJobsAPI;
import instavans.sanchit.instavans.restapi.models.SignupAPI;
import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by sanchitjain on 05/03/16.
 */


public interface APIService {


    @FormUrlEncoded
    @POST("signup")
    Call<SignupAPI> userSignUp(@Field("mobile") String mobile,@Field("name") String Name );

    @FormUrlEncoded
    @POST("login")
    Call<SignupAPI> userLogin(@Field("porterId") String porterId);

    @FormUrlEncoded
    @POST("porterLocation")
    Call<SignupAPI> useLatLng(@Field("porterId") String porterId , @Field("latitude") Double Latitude, @Field("longitude") Double longitude );


    @FormUrlEncoded
    @POST("findJobs")
    Call<FindJobsAPI> getJobs(@Field("porterId") String pid);

    @FormUrlEncoded
    @POST("jobAction")
    Call<ActionAPI> setJobAction(@Field("porterId") String pid,@Field("shipperId") String shipperId,@Field("job_number") String jobnumber,@Field("action") int action );

    @FormUrlEncoded
    @POST("jobActivity")
    Call<ActionAPI> setJobActivity(@Field("porterId") String pid,@Field("shipperId") String shipperId,@Field("job_number") String jobnumber,@Field("action") int action );


    @GET("actionTakenJobs/{porterid}/{status}")
    Call<ActionListAPI> getActiveJobs(@Path("porterid") String porterid ,@Path("status") String status);

    @FormUrlEncoded
    @POST("sms")
    Call<ChatSendAPI> sendMsg(@Field("message") String message,@Field("job_number") String jobNumber);

    @FormUrlEncoded
    @POST("readSMS")
    Call<ChatRecieveAPI> readSms(@Field("job_number") String jobNumber);





}

