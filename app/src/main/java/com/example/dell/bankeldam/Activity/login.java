package com.example.dell.bankeldam.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dell.bankeldam.Helper.SharedPereferenceClass;
import com.example.dell.bankeldam.Model.User_Information;
import com.example.dell.bankeldam.Model.User_Login;
import com.example.dell.bankeldam.Presenter.User_Login_Presenter;
import com.example.dell.bankeldam.R;
import com.example.dell.bankeldam.View.Login_View;

public class login extends AppCompatActivity implements Login_View {

    Button Sign_in;
    User_Login_Presenter user_login;
    ProgressBar progressBar;
    EditText E_phone, E_passowrd;
    String token;
    SharedPereferenceClass sharedPereferenceClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPereferenceClass = new SharedPereferenceClass();
        Sign_in = findViewById(R.id.Sign_in);
        E_phone = findViewById(R.id.phone);
        E_passowrd = findViewById(R.id.password);
        user_login = new User_Login_Presenter(getBaseContext(), this);
        progressBar = findViewById(R.id.ProgrossLogin);
        signIn();
    }

    public void signIn() {
        Sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String ephone = E_phone.getText().toString();
                String epassword = E_passowrd.getText().toString();
                user_login.Login(ephone, epassword);

            }
        });

    }

    @Override
    public void Get_User_Information(User_Information user_informations) {
        progressBar.setVisibility(View.GONE);
        String user_Name = user_informations.getName();
        Toast.makeText(this, "" + user_Name, Toast.LENGTH_SHORT).show();
        Intent signin = new Intent(login.this, Home.class);
        startActivity(signin);
        sharedPereferenceClass.storeKey(this, "client_register_success", "true");

    }

    @Override
    public void User_token(User_Login user_login) {
        token = user_login.getApiToken();
        sharedPereferenceClass.storeKey(this, "API_TOKEN", token);
        Toast.makeText(this, token, Toast.LENGTH_LONG).show();
    }

    @Override
    public void Error() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "login failed", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(login.this, Register.class));
        finish();
    }

    public void register(View view) {
        Intent registerntent = new Intent(login.this, Register.class);
        startActivity(registerntent);
    }
}
