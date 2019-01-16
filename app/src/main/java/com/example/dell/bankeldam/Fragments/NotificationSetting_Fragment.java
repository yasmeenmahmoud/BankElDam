package com.example.dell.bankeldam.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.bankeldam.Activity.Home;
import com.example.dell.bankeldam.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationSetting_Fragment extends Fragment {


    public NotificationSetting_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view=inflater.inflate(R.layout.fragment_notificationsetting_, container, false);
        return  view;
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.back) {
//            Intent intentnoty = new Intent(getActivity(), Home.class);
//            startActivity(intentnoty);
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
    @Override
    public void onResume() {
        super.onResume();
        Home home = (Home) getActivity();
        home.getSupportActionBar().setTitle("اعدادات الاشعارات");
        home.toggle.setDrawerIndicatorEnabled(false);
        home.toggle.setHomeAsUpIndicator(null);
        home.getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }
}
