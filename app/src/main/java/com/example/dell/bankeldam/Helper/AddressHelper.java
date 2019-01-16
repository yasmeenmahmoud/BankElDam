package com.example.dell.bankeldam.Helper;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dell.bankeldam.Model.CityDataResponse;
import com.example.dell.bankeldam.Model.governorateDataResponse;
import com.example.dell.bankeldam.Retrofit.ApiCLint;
import com.example.dell.bankeldam.Retrofit.Apiinterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddressHelper {
    public Spinner governatepinner;
    public Spinner cityspinner;
    private Context context;
    private int id;
   private ArrayList<String> citiessname = new ArrayList<>();
  private   SharedPereferenceClass sharedPereferenceClass=new SharedPereferenceClass();

    public AddressHelper(Context context, Spinner governatepinner, Spinner cityspinner) {
        this.context = context;
        this.governatepinner = governatepinner;
        this.cityspinner = cityspinner;

    }

    public AddressHelper(Context context, Spinner cityspinner) {
        this.context = context;
        this.cityspinner = cityspinner;

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
                            governatepinner.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, governoratessname));
                        }
                        governatepinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                citiessname.add("اختر المدينه");
                for (int i = 0; i < citydata.getData().size(); i++) {
                    citiessname.add(citydata.getData().get(i).getName());
                    citiesid.add(citydata.getData().get(i).getId());
                }
                cityspinner.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, citiessname));
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
    public void cityiesSpinner(final Spinner cityspinner) {
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<CityDataResponse> call = apiInterface.callcities();
        call.enqueue(new Callback<CityDataResponse>() {
            @Override
            public void onResponse(Call<CityDataResponse> call, Response<CityDataResponse> response) {
                CityDataResponse citydata = response.body();
                ArrayList<String> citiessname = new ArrayList<>();
                final ArrayList<Integer> citiesid = new ArrayList<>();
                citiessname.add(sharedPereferenceClass.getStoredKey(context, "CLIENT_CITY_Name"));
                for (int i = 0; i < citydata.getData().size(); i++) {
                    citiessname.add(citydata.getData().get(i).getName());
                    citiesid.add(citydata.getData().get(i).getId());
                }
                cityspinner.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, citiessname));
                cityspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long Id) {
                        if (position != 0) {
                            Id = citiesid.get(position-1);
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
    public void SetSpinnerSelection(Spinner spinner,String text) {
        for (int i = 0; i < citiessname.size(); i++) {
            if (citiessname.get(i).equals(text)) {
                citiessname.add(text);
                spinner.setSelection(i);
            }
        }
    }
    public void selectGovernData() {
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<governorateDataResponse> call = apiInterface.callgovernorates();
        call.enqueue(new Callback<governorateDataResponse>() {
            @Override
            public void onResponse(Call<governorateDataResponse> call, Response<governorateDataResponse> response) {
                governorateDataResponse governatedata = response.body();
                ArrayList<String> governoratessname = new ArrayList<>();
                final ArrayList<Integer> governoratesid = new ArrayList<>();
                try {
                    if (governatedata != null) {
                        governoratessname.add( sharedPereferenceClass.getStoredKey(context, "CLIENT_GOVERN_NAME"));
                        for (int i = 0; i < governatedata.getData().size(); i++) {
                            governoratessname.add(governatedata.getData().get(i).getName());
                            governoratesid.add(governatedata.getData().get(i).getId());
                            governatepinner.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, governoratessname));
                        }
                        governatepinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

}
