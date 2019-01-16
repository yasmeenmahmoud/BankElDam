package com.example.dell.bankeldam.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dell.bankeldam.Activity.Home;
import com.example.dell.bankeldam.Model.Details_Donation_Response;
import com.example.dell.bankeldam.Model.DonationRequest_Data;
import com.example.dell.bankeldam.R;
import com.example.dell.bankeldam.Retrofit.ApiCLint;
import com.example.dell.bankeldam.Retrofit.Apiinterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonationDetails_Fragment extends Fragment {
    View view;
    public DonationRequest_Data donationDetailsData = new DonationRequest_Data();
    @BindView(R.id.D_patientName)
    TextView DPatientName;
    @BindView(R.id.D_patientAge)
    TextView DPatientAge;
    @BindView(R.id.D_blodtype)
    TextView DBlodtype;
    @BindView(R.id.D_bagsNum)
    TextView DBagsNum;
    @BindView(R.id.D_hospitalname)
    TextView DHospitalname;
    @BindView(R.id.D_hospitaladdres)
    TextView DHospitaladdres;
    @BindView(R.id.D_phonenumber)
    TextView DPhonenumber;
    @BindView(R.id.D_nots)
    TextView DNots;
    Unbinder unbinder;
    @BindView(R.id.call_btn)
    Button callBtn;
//    @BindView(R.id.title)
//    TextView title;
//    @BindView(R.id.back_btn)
//    ImageButton backBtn;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    public DonationDetails_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_donation_details_, container, false);
        int id = donationDetailsData.getId();
        unbinder = ButterKnife.bind(this, view);
        getDetails("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27", id);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //hide navigation drawer  icon
        Home home = (Home) getActivity();
        home.getSupportActionBar().setTitle("طلب تبرع: " + donationDetailsData.getPatientName());
        home.toggle.setDrawerIndicatorEnabled(false);
        home.toggle.setHomeAsUpIndicator(null);
        home.getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }

    private void getDetails(String apiToken, int id) {
        Apiinterface apiinterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<Details_Donation_Response> call = apiinterface.getPatientDetails(apiToken, id);
        call.enqueue(new Callback<Details_Donation_Response>() {
            @Override
            public void onResponse(Call<Details_Donation_Response> call, Response<Details_Donation_Response> response) {
                Details_Donation_Response detailsDonationResponse = response.body();
                try {
                if (detailsDonationResponse != null) {
                    Log.i("MyResponse", response.body().getMsg());
                    // DPatientName.setText(detailsDonationResponse.getData().getPatientName());
                    DPatientName.setText("الاسم:" + donationDetailsData.getPatientName());
                    DPatientAge.setText("العمر:" + detailsDonationResponse.getData().getPatientAge());
                    DBlodtype.setText("فصيلة الدم:" + detailsDonationResponse.getData().getBloodType());
                    DBagsNum.setText("عدد الاكياس المطلوبه:" + detailsDonationResponse.getData().getBagsNum());
                    DHospitalname.setText("المستشفى:" + detailsDonationResponse.getData().getHospitalName());
                    DHospitaladdres.setText("عنوان المستشفى:" + detailsDonationResponse.getData().getHospitalAddress());
                    DPhonenumber.setText("رقم الجوال:" + detailsDonationResponse.getData().getPhone());
                    DNots.setText("التفاصيل:" + detailsDonationResponse.getData().getNotes());
                }}catch (Exception e){}
            }

            @Override
            public void onFailure(Call<Details_Donation_Response> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }

    @OnClick(R.id.call_btn)
    public void onViewClicked() {
        dialContactPhone(DPhonenumber.getText().toString());
    }
}
