package com.example.dell.bankeldam.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dell.bankeldam.Adapter.NotificationAdapter;
import com.example.dell.bankeldam.Activity.Home;
import com.example.dell.bankeldam.Model.Notification_Data;
import com.example.dell.bankeldam.Model.Notification_Response;
import com.example.dell.bankeldam.R;
import com.example.dell.bankeldam.Retrofit.ApiCLint;
import com.example.dell.bankeldam.Retrofit.Apiinterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationList_Fragment extends Fragment {
    NotificationAdapter notificationAdapter;
    List<Notification_Data> notificationdata = new ArrayList<>();
    @BindView(R.id.notificationrecyclerview)
    RecyclerView notificationrecyclerview;
    Unbinder unbinder;
String api_token;
    public NotificationList_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification_list_, container, false);
        unbinder = ButterKnife.bind(this, view);
        api_token="Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27";
        getnotification();
        return view;
    }

    private void getnotification() {
        Apiinterface apiinterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<Notification_Response> call = apiinterface.getnotificationDetails(api_token);
        call.enqueue(new Callback<Notification_Response>() {
            @Override
            public void onResponse(Call<Notification_Response> call, Response<Notification_Response> response) {
                Notification_Response notificationResponse = response.body();
                if (notificationResponse != null) {
                    notificationdata = notificationResponse.getData().getData();
                    Toast.makeText(getContext(), notificationResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
                notificationrecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
                notificationAdapter = new NotificationAdapter(getContext(), notificationdata);
                notificationrecyclerview.setAdapter(notificationAdapter);
            }

            @Override
            public void onFailure(Call<Notification_Response> call, Throwable t) {
                Toast.makeText(getContext(), "failed added successeffully", Toast.LENGTH_SHORT).show();

            }
        });

    }
    @Override
    public void onResume() {
        super.onResume();
        Home home = (Home) getActivity();
        home.getSupportActionBar().setTitle("التنبيهات");
        home.toggle.setDrawerIndicatorEnabled(false);
        home.toggle.setHomeAsUpIndicator(null);
        home.getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
