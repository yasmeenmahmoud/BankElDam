package com.example.dell.bankeldam.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell.bankeldam.Adapter.DonationRequests_Adapter;
import com.example.dell.bankeldam.Activity.Home;
import com.example.dell.bankeldam.Model.DonationRequest_Data;
import com.example.dell.bankeldam.Model.Donation_responseese;
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
public class DonationRequests_Fragment extends Fragment {


    @BindView(R.id.donationrequestsrecycler)
    ListView donationrequestsrecycler;
    Unbinder unbinder;
    DonationRequests_Adapter donationRequests_adapter;
    List<DonationRequest_Data> requests = new ArrayList<>();
    String api_token;

    public DonationRequests_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donation_requests_, container, false);
        unbinder = ButterKnife.bind(this, view);
        addfileData();
        return view;
    }

    private void addfileData() {
        Apiinterface apiinterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<Donation_responseese> call = apiinterface.getdonationRequests();
        call.enqueue(new Callback<Donation_responseese>() {
            @Override
            public void onResponse(Call<Donation_responseese> call, Response<Donation_responseese> response) {
                Donation_responseese donation_responseese = response.body();
                if (donation_responseese != null) {
                    requests = donation_responseese.getData().getData();
                    donationRequests_adapter = new DonationRequests_Adapter(getContext(), R.layout.listview_items
                            , requests);
                    donationrequestsrecycler.setAdapter(donationRequests_adapter);
                }

            }

            @Override
            public void onFailure(Call<Donation_responseese> call, Throwable t) {
                Toast.makeText(getContext(), "failed added successeffully", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        Home home = (Home) getActivity();
        home.getSupportActionBar().setTitle("");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
