package com.example.dell.bankeldam.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dell.bankeldam.Helper.SharedPereferenceClass;
import com.example.dell.bankeldam.Model.CityDataResponse;
import com.example.dell.bankeldam.Model.governorateDataResponse;
import com.example.dell.bankeldam.Presenter.User_Register_Presenter;
import com.example.dell.bankeldam.R;
import com.example.dell.bankeldam.Retrofit.ApiCLint;
import com.example.dell.bankeldam.Retrofit.Apiinterface;
import com.example.dell.bankeldam.View.Register_view;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity implements Register_view {
    Calendar myCalendar;
    ProgressBar progressBar;
    Spinner cities, governorates;
    SharedPereferenceClass sharedPereferenceClass;
    User_Register_Presenter user_register_presenter;
    String _name;
    String _email;
    String _bloodtype;
    String _phone;
    String _password;
    String _birthdate;
    String _city;
    String _cityname;
    String governname;
    String _donationlastdate;
    String _confirmpassword;
    EditText name, email, birthdate, blodtype, phone, password, confirmpassword, donationlastdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        sharedPereferenceClass = new SharedPereferenceClass();
        progressBar = findViewById(R.id.Progrossregister);
        name = findViewById(R.id.name);
        birthdate = findViewById(R.id.birthdate);
        email = findViewById(R.id.email);
        blodtype = findViewById(R.id.blodtype);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        donationlastdate = findViewById(R.id.lastdate);
        confirmpassword = findViewById(R.id.confirm_password);
        spinnerData();
        user_register_presenter = new User_Register_Presenter(Register.this, this);
    }

    public void showcalender(View view) {
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        final DatePickerDialog datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date =
                        String.valueOf(year) + "-0" + String.valueOf(monthOfYear) + "-" + String.valueOf(dayOfMonth);
                donationlastdate.setText(date);
            }
        }, yy, mm, dd);
        datePicker.show();
    }

    public void signhome(View view) {
        progressBar.setVisibility(View.VISIBLE);
        _cityname=cities.getSelectedItem().toString();
        governname=governorates.getSelectedItem().toString();
        _name = name.getText().toString();
        _email = email.getText().toString();
        _bloodtype = blodtype.getText().toString();
        _phone = phone.getText().toString();
        _password = password.getText().toString();
        _birthdate = birthdate.getText().toString();
        _city = String.valueOf(cities.getSelectedItemId());
        _donationlastdate = donationlastdate.getText().toString();
        _confirmpassword = confirmpassword.getText().toString();
        user_register_presenter.Register(_name, _email,
                _bloodtype, _phone
                , _password, _birthdate, _city
                , _confirmpassword, _donationlastdate);
    }

    @Override
    public void success() {
        Toast.makeText(this, "register success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Register.this, Home.class);
        progressBar.setVisibility(View.GONE);
        startActivity(intent);
        sharedPereferenceClass.storeKey(this, "client_register_success", "true");
        saveRegisteredClient();
    }

    @Override
    public void Error() {
        Toast.makeText(this, "register failed", Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
    }

    public void spinnerData() {
        governorates = findViewById(R.id.governspinner);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<governorateDataResponse> call = apiInterface.callgovernorates();
        call.enqueue(new Callback<governorateDataResponse>() {
            @Override
            public void onResponse(Call<governorateDataResponse> call, Response<governorateDataResponse> response) {
                governorateDataResponse governatedata = response.body();
                ArrayList<String> governoratessname = new ArrayList<>();
                final ArrayList<Integer> governoratesid = new ArrayList<>();
                governoratessname.add("اختر المحافظه");
                for (int i = 0; i < governatedata.getData().size(); i++) {
                    governoratessname.add(governatedata.getData().get(i).getName());
                    governoratesid.add(governatedata.getData().get(i).getId());
                }
                governorates.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, governoratessname));
                governorates.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

            @Override
            public void onFailure(Call<governorateDataResponse> call, Throwable t) {
            }
        });

    }

    public void citySpinner(int id) {
        cities = findViewById(R.id.cityspinner);
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
                cities.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, citiessname));
                cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void saveRegisteredClient() {
        sharedPereferenceClass.storeKey(this, "CLIENT_NAME", _name);
        sharedPereferenceClass.storeKey(this, "CLIENT_PASSWORD", _password);
        sharedPereferenceClass.storeKey(this, "CLIENT_EMAIL", _email);
        sharedPereferenceClass.storeKey(this, "CLIENT_PHONE", _phone);
        sharedPereferenceClass.storeKey(this, "CLIENT_BIRTHDATE", _birthdate);
        sharedPereferenceClass.storeKey(this, "CLIENT_bloodtype", _bloodtype);
        sharedPereferenceClass.storeKey(this, "CLIENT_CITY", _city);
        sharedPereferenceClass.storeKey(this, "CLIENT_CITY_Name", _cityname);
        sharedPereferenceClass.storeKey(this, "CLIENT_GOVERN_NAME", governname);
        sharedPereferenceClass.storeKey(this, "CLIENT_DONATION_LASTDATE", _donationlastdate);

    }
}


