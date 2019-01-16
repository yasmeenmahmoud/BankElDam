package com.example.dell.bankeldam.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.dell.bankeldam.Helper.SharedPereferenceClass;
import com.example.dell.bankeldam.R;

public class appinfo extends AppCompatActivity {
SharedPereferenceClass sharedPereferenceClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appinfo);
sharedPereferenceClass=new SharedPereferenceClass();
    }

    public void gotologin(View view) {
        if (        sharedPereferenceClass.getStoredKey(this, "client_register_success").equals("true"))
        { Intent gologin=new Intent(appinfo.this,Home.class);
            startActivity(gologin);}else
        { Intent gologin=new Intent(appinfo.this,login.class);
        startActivity(gologin);}
    }

}
