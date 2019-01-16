package com.example.dell.bankeldam.Retrofit;

import com.example.dell.bankeldam.Model.CityDataResponse;
import com.example.dell.bankeldam.Model.CreateDonationRequest_Response;
import com.example.dell.bankeldam.Model.Details_Donation_Response;
import com.example.dell.bankeldam.Model.DonationRequest_Data;
import com.example.dell.bankeldam.Model.DonationRequest_Response;
import com.example.dell.bankeldam.Model.Donation_responseese;
import com.example.dell.bankeldam.Model.Favourite_Response;
import com.example.dell.bankeldam.Model.Notification_Response;
import com.example.dell.bankeldam.Model.PostDetails_Response;
import com.example.dell.bankeldam.Model.Posts_responsess;
import com.example.dell.bankeldam.Model.UserLoginResponse;
import com.example.dell.bankeldam.Model.UserRegisterResponse;
import com.example.dell.bankeldam.Model.governorateDataResponse;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface Apiinterface {
    @POST("login")
    @FormUrlEncoded
    Call<UserLoginResponse> userLogin(@Field("phone") String phone,
                                      @Field("password") String password
    );

    //    @POST("login")
//    Call<UserLoginResponse> Login(@QueryMap Map<String, String> queryMab);
    @POST("register")
    Call<UserRegisterResponse> Register(@QueryMap Map<String, String> queryMab);

    @POST("register")
    Call<UserRegisterResponse> getRegister();

    @GET("governorates")
    Call<governorateDataResponse> callgovernorates();

    @GET("cities?governorate_id=1")
    Call<CityDataResponse> callcities();

    @GET("donation-requests?api_token=Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27&blood_type=A-&city_id=1")
    Call<Donation_responseese> getdonationRequests();

    @GET("posts?api_token=Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27")
    Call<Posts_responsess> getPosts(@Query("api_token") String apitoken);

    //    @GET("donation-request?api_token=Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27&donation_id=1")
    @GET("donation-request")
    Call<Details_Donation_Response> getPatientDetails(@Query("api_token") String api_token, @Query("donation_id") int donation_id);

    @GET("post")
    Call<PostDetails_Response> getpostsDetails(@Query("api_token") String api_token, @Query("post_id") int post_id);

    @GET("my-favourites?api_token=Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27")
    Call<Favourite_Response> getFavourites();

    @GET("notifications")
    Call<Notification_Response> getnotificationDetails(@Query("api_token") String api_token);

    @POST("donation-request/create")
    @FormUrlEncoded
    Call<CreateDonationRequest_Response> sendDonationRequest(@Field("api_token") String apitoken,
                                                             @Field("patient_name") String patentname,
                                                             @Field("patient_age") String patientage,
                                                             @Field("phone") String phone,
                                                             @Field("blood_type") String blodtype,
                                                             @Field("bags_num") String bagsnum,
                                                             @Field("hospital_name") String hospitalname,
                                                             @Field("hospital_address") String hospital_address,
                                                             @Field("city_id") String city_id,
                                                             @Field("notes") String notes);


}
