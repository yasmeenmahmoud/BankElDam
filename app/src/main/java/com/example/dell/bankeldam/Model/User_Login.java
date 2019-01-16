package com.example.dell.bankeldam.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User_Login {
    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("client")
    @Expose
    private User_Information client;

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public User_Information getClient() {
        return client;
    }

    public void setClient(User_Information client) {
        this.client = client;
    }

}
