package com.example.dell.bankeldam.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.example.dell.bankeldam.Model.UserRegisterResponse;
import com.example.dell.bankeldam.Retrofit.ApiCLint;
import com.example.dell.bankeldam.Retrofit.Apiinterface;
import com.example.dell.bankeldam.View.Register_view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_Register_Presenter {
    Register_view register_view;
    Context context;

    public User_Register_Presenter(Context context, Register_view register_view) {
        this.register_view = register_view;
        this.context = context;
    }

    public void Register(String name, String email, String blodtype, String phone, String password,
                         String birth_date, String city,
                         String password_confirmation, String donationlastldate) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("name", name);
        queryMap.put("email", email);
        queryMap.put("blood_type", blodtype);
        queryMap.put("phone", phone);
        queryMap.put("password", password);
        queryMap.put("birth_date", birth_date);
        queryMap.put("city_id", city);
        queryMap.put("password_confirmation", password_confirmation);
        queryMap.put("donation_last_date", donationlastldate);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<UserRegisterResponse> call = apiInterface.Register(queryMap);
        call.enqueue(new Callback<UserRegisterResponse>() {
            @Override
            public void onResponse(Call<UserRegisterResponse> call, Response<UserRegisterResponse> response) {
                UserRegisterResponse userRegisterResponse = response.body();
                if (userRegisterResponse != null) {
                    Log.i("MyResponse", userRegisterResponse.getMsg());
                    register_view.success();
                } else {
                    register_view.Error();
                }
            }

            @Override
            public void onFailure(Call<UserRegisterResponse> call, Throwable t) {

            }
        });
    }

}
