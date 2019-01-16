package com.example.dell.bankeldam.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.dell.bankeldam.Fragments.DonationDetails_Fragment;
import com.example.dell.bankeldam.Model.DonationDetails_Data;
import com.example.dell.bankeldam.Model.DonationRequest_Data;
import com.example.dell.bankeldam.R;

import java.util.List;

public class DonationRequests_Adapter extends ArrayAdapter<DonationRequest_Data> {
    // private int resourceLayout;
    DonationRequest_Data donationRequest_data;
    DonationDetails_Data donationDetailsData;
    private Context mContext;
    private LayoutInflater mInflater;

    public DonationRequests_Adapter(Context context, int resource, List<DonationRequest_Data> patientdata) {
        super(context, resource, patientdata);
        // this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //   View v = convertView;

        if (convertView == null) {
            // LayoutInflater vi;
            // vi = LayoutInflater.from(mContext);
            //  convertView = vi.inflate(R.layout.listview_items, null);
            mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = mInflater.inflate(R.layout.listview_items, null);

        }

        final DonationRequest_Data p = getItem(position);


        TextView tv_blodtypee = (TextView) convertView.findViewById(R.id.blodtypee);
        tv_blodtypee.setText(p.getBloodType());
        TextView tv_patientname = (TextView) convertView.findViewById(R.id.patientname);
        tv_patientname.setText("اسم الحاله:" + p.getPatientName());

        TextView tv_patienthospital = (TextView) convertView.findViewById(R.id.patienthospital);
        tv_patienthospital.setText("اسم المستشفى:" + p.getHospitalName());

        TextView tv_patientcity = (TextView) convertView.findViewById(R.id.patientcity);
        tv_patientcity.setText("عنوان المستشفى:" + p.getHospitalAddress());
        Button btn_patientdetails = (Button) convertView.findViewById(R.id.details);
        Button btn_call = convertView.findViewById(R.id.calll);
        btn_patientdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((AppCompatActivity) mContext).getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                DonationDetails_Fragment donationDetails=new DonationDetails_Fragment();
                donationDetails.donationDetailsData=p;
                transaction.replace(R.id.flContent, donationDetails);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialContactPhone(p.getPhone());


            }
        });

        return convertView;
    }

    private void dialContactPhone(final String phoneNumber) {
        mContext.startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }

}
