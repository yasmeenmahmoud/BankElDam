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
public class Report_Fragment extends Fragment {


    public Report_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view=inflater.inflate(R.layout.fragment_report_, container, false);

        return  view;
    }
    @Override
    public void onResume() {
        super.onResume();
        Home home = (Home) getActivity();
        home.getSupportActionBar().setTitle("ابلاغ");
        home.toggle.setDrawerIndicatorEnabled(false);
        home.toggle.setHomeAsUpIndicator(null);
        home.getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }
}
