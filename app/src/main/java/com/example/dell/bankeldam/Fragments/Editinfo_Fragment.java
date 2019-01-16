package com.example.dell.bankeldam.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.bankeldam.Helper.AddressHelper;
import com.example.dell.bankeldam.Helper.SharedPereferenceClass;
import com.example.dell.bankeldam.Helper.ShowCalender_Helper;
import com.example.dell.bankeldam.Activity.Home;
import com.example.dell.bankeldam.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Editinfo_Fragment extends Fragment {

    AddressHelper addressHelper;
    @BindView(R.id.I_name)
    EditText IName;
    @BindView(R.id.I_email)
    EditText IEmail;
    @BindView(R.id.I_date)
    EditText IDate;
    @BindView(R.id.I_blodtype)
    EditText IBlodtype;
//    @BindView(R.id.governspinner)
//    Spinner governspinner;
    @BindView(R.id.cityspinner)
    Spinner cityspinner;
    @BindView(R.id.I_phone)
    EditText IPhone;
    @BindView(R.id.I_password)
    EditText IPassword;
    @BindView(R.id.I_confiempassword)
    EditText IConfiempassword;
    @BindView(R.id.mydatee)
    TextView mydatee;
    @BindView(R.id.editdata)
    Button editdata;
    Unbinder unbinder;
    SharedPereferenceClass sharedPereferenceClass;
    String _name;
    String _email;
    String _bloodtype;
    String _phone;
    String _password;
    String _birthdate;
    String _city;
    String _cityname;
    String _donationlastdate;
    String _confirmpassword;

    public Editinfo_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_editinfo_, container, false);
        unbinder = ButterKnife.bind(this, view);
        addressHelper = new AddressHelper(getContext(), cityspinner);
        sharedPereferenceClass = new SharedPereferenceClass();
        getInfo();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Home home = (Home) getActivity();
        home.getSupportActionBar().setTitle("تعديل البيانات");
        home.toggle.setDrawerIndicatorEnabled(false);
        home.toggle.setHomeAsUpIndicator(null);
        home.getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }

    public void getInfo() {
        IName.setText(sharedPereferenceClass.getStoredKey(getContext(), "CLIENT_NAME"));
        IEmail.setText(sharedPereferenceClass.getStoredKey(getContext(), "CLIENT_EMAIL"));
        IDate.setText(sharedPereferenceClass.getStoredKey(getContext(), "CLIENT_BIRTHDATE"));
        IBlodtype.setText(sharedPereferenceClass.getStoredKey(getContext(), "CLIENT_bloodtype"));
        IPhone.setText(sharedPereferenceClass.getStoredKey(getContext(), "CLIENT_PHONE"));
        IPassword.setText(sharedPereferenceClass.getStoredKey(getContext(), "CLIENT_PASSWORD"));
        IConfiempassword.setText(sharedPereferenceClass.getStoredKey(getContext(), "CLIENT_PASSWORD"));
        mydatee.setText(sharedPereferenceClass.getStoredKey(getContext(), "CLIENT_DONATION_LASTDATE"));
        addressHelper.spinnerData();
        addressHelper.selectGovernData();
        addressHelper.cityiesSpinner(cityspinner);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.mydatee, R.id.editdata})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mydatee:
                ShowCalender_Helper showCalender_helper = new ShowCalender_Helper(getContext());
                showCalender_helper.showCalender(mydatee);
                break;
            case R.id.editdata:
                editInfo();
                Toast.makeText(getContext(), "تم التعديل بنجاح ..", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void editInfo() {
        _cityname = cityspinner.getSelectedItem().toString();
        _name = IName.getText().toString();
        _email = IEmail.getText().toString();
        _bloodtype = IBlodtype.getText().toString();
        _phone = IPhone.getText().toString();
        _password = IPassword.getText().toString();
        _birthdate = IDate.getText().toString();
        _city = String.valueOf(cityspinner.getSelectedItemId());
        _donationlastdate = mydatee.getText().toString();
        _confirmpassword = IConfiempassword.getText().toString();
        sharedPereferenceClass.storeKey(getContext(), "CLIENT_NAME", _name);
        sharedPereferenceClass.storeKey(getContext(), "CLIENT_PASSWORD", _password);
        sharedPereferenceClass.storeKey(getContext(), "CLIENT_EMAIL", _email);
        sharedPereferenceClass.storeKey(getContext(), "CLIENT_PHONE", _phone);
        sharedPereferenceClass.storeKey(getContext(), "CLIENT_BIRTHDATE", _birthdate);
        sharedPereferenceClass.storeKey(getContext(), "CLIENT_bloodtype", _bloodtype);
        sharedPereferenceClass.storeKey(getContext(), "CLIENT_CITY", _city);
        sharedPereferenceClass.storeKey(getContext(), "CLIENT_CITY_Name", _cityname);
        sharedPereferenceClass.storeKey(getContext(), "CLIENT_DONATION_LASTDATE", _donationlastdate);
    }
}
