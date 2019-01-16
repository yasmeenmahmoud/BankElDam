package com.example.dell.bankeldam.Presenter;

import android.content.Context;
import android.util.Log;

import com.example.dell.bankeldam.Model.UserLoginResponse;
import com.example.dell.bankeldam.Model.User_Information;
import com.example.dell.bankeldam.Retrofit.ApiCLint;
import com.example.dell.bankeldam.Retrofit.Apiinterface;
import com.example.dell.bankeldam.View.Login_View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_Login_Presenter {

    Login_View login_view;
    Context context;

    public User_Login_Presenter(Context context, Login_View login_view) {
        this.login_view = login_view;
        this.context = context;
    }

    public void Login(String phone, String password) {
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<UserLoginResponse> call = apiInterface.userLogin(phone, password);
        call.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                UserLoginResponse userLoginResponse = response.body();
                try {
                    if (userLoginResponse != null) {
                        login_view.Get_User_Information(userLoginResponse.getData().getClient());
                        login_view.User_token(userLoginResponse.getData());
                        Log.d("LOGIN_RESPONSE", userLoginResponse.getMsg());
                    }
                } catch (Exception e) {
                    login_view.Error();
                }
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                login_view.Error();
            }
        });
    }
}
