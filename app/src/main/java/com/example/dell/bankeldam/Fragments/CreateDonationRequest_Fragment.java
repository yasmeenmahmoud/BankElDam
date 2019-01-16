package com.example.dell.bankeldam.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dell.bankeldam.Activity.Home;
import com.example.dell.bankeldam.Model.CityDataResponse;
import com.example.dell.bankeldam.Model.CreateDonationRequest_Response;
import com.example.dell.bankeldam.Model.governorateDataResponse;
import com.example.dell.bankeldam.R;
import com.example.dell.bankeldam.Retrofit.ApiCLint;
import com.example.dell.bankeldam.Retrofit.Apiinterface;

import java.util.ArrayList;

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
public class CreateDonationRequest_Fragment extends Fragment {

    String api_token;
    View view;
    @BindView(R.id.patient_name)
    EditText patientName;
    @BindView(R.id.patient_age)
    EditText patientAge;
    @BindView(R.id.patient_blodtype)
    EditText patientBlodtype;
    @BindView(R.id.bagsnum)
    EditText bagsnum;
    @BindView(R.id.patient_hospitalname)
    EditText patientHospitalname;
    @BindView(R.id.patient_hospitaladdress)
    EditText patientHospitaladdress;
    @BindView(R.id.governspinner)
    Spinner governspinner;
    @BindView(R.id.cityspinner)
    Spinner cityspinner;
    @BindView(R.id.sendrequests)
    Button sendrequests;
    Unbinder unbinder;
    String _patientname;
    String _patientage;
    String _patientblodtype;
    String _bagnum;
    String _patienthospitalname;
    String _patienthospitaladdress;
    String _patientcity;
    String _patientphone;
    String _notes;
    @BindView(R.id.patientphone)
    EditText patientphone;
    @BindView(R.id.patientnotes)
    EditText patientnotes;
//    @BindView(R.id.title)
//    TextView title;
//    @BindView(R.id.back_btn)
//    ImageButton backBtn;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;


    public CreateDonationRequest_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_donation_request_, container, false);
        api_token = "Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27";

        // Inflate the layout for this fragment
        unbinder = ButterKnife.bind(this, view);

        //title.setText("طلب تبرع");
        spinnerData();
        return view;
    }

    private void getPostsDetails() {
        Apiinterface apiinterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<CreateDonationRequest_Response> call = apiinterface.sendDonationRequest(api_token, _patientname, _patientage, _patientphone
                , _patientblodtype, _bagnum, _patienthospitalname, _patienthospitaladdress, _patientcity, _notes);
        call.enqueue(new Callback<CreateDonationRequest_Response>() {
            @Override
            public void onResponse(Call<CreateDonationRequest_Response> call, Response<CreateDonationRequest_Response> response) {
                CreateDonationRequest_Response createDonationRequest_response = response.body();
                if (createDonationRequest_response != null) {
                    Log.i("MyResponse", createDonationRequest_response.getMsg());
                    Toast.makeText(getContext(), createDonationRequest_response.getMsg(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CreateDonationRequest_Response> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Home home = (Home) getActivity();
        home.getSupportActionBar().setTitle("طلب تبرع");
        home.toggle.setDrawerIndicatorEnabled(false);
        home.toggle.setHomeAsUpIndicator(null);
        home.getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.sendrequests)
    public void onViewClicked() {
        _patientname = patientName.getText().toString();
        _patientage = patientAge.getText().toString();
        _patientblodtype = patientBlodtype.getText().toString();
        _bagnum = bagsnum.getText().toString();
        _patienthospitalname = patientHospitalname.getText().toString();
        _patienthospitaladdress = patientHospitaladdress.getText().toString();
        _patientphone = patientphone.getText().toString();
        _notes = patientnotes.getText().toString();
        _patientcity = String.valueOf(cityspinner.getSelectedItemId());
        getPostsDetails();
    }

    public void spinnerData() {
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<governorateDataResponse> call = apiInterface.callgovernorates();
        call.enqueue(new Callback<governorateDataResponse>() {
            @Override
            public void onResponse(Call<governorateDataResponse> call, Response<governorateDataResponse> response) {
                governorateDataResponse governatedata = response.body();
                ArrayList<String> governoratessname = new ArrayList<>();
                final ArrayList<Integer> governoratesid = new ArrayList<>();
                governoratessname.add("اختر المحافظه");
                try {
                    if (governatedata != null) {
                        for (int i = 0; i < governatedata.getData().size(); i++) {
                            governoratessname.add(governatedata.getData().get(i).getName());
                            governoratesid.add(governatedata.getData().get(i).getId());
                            governspinner.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, governoratessname));
                        }
                        governspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long Id) {
                                if (position != 0) {
                                    int id = governoratesid.get(position - 1);
                                    citySpinner(id);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }
                } catch (Exception e) {
                }


            }

            @Override
            public void onFailure(Call<governorateDataResponse> call, Throwable t) {
            }
        });

    }

    public void citySpinner(int id) {
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<CityDataResponse> call = apiInterface.callcities();
        call.enqueue(new Callback<CityDataResponse>() {
            @Override
            public void onResponse(Call<CityDataResponse> call, Response<CityDataResponse> response) {
                CityDataResponse citydata = response.body();
                ArrayList<String> citiessname = new ArrayList<>();
                final ArrayList<Integer> citiesid = new ArrayList<>();
                for (int i = 0; i < citydata.getData().size(); i++) {
                    citiessname.add(citydata.getData().get(i).getName());
                    citiesid.add(citydata.getData().get(i).getId());
                }
                cityspinner.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, citiessname));
                cityspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long Id) {
                        if (position != 0) {
                            Id = citiesid.get(position);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }

            @Override
            public void onFailure(Call<CityDataResponse> call, Throwable t) {
            }
        });

    }

}
