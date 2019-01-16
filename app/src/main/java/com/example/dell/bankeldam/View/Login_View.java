package com.example.dell.bankeldam.View;

import com.example.dell.bankeldam.Model.User_Information;
import com.example.dell.bankeldam.Model.User_Login;

import java.util.List;

public interface Login_View {

    void Get_User_Information(User_Information user_informations);
    void User_token(User_Login user_token);
    void Error();
}
