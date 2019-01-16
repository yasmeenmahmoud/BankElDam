package com.example.dell.bankeldam.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User_Register {

    @SerializedName("name")
    @Expose
    private List<String> name = null;
    @SerializedName("city_id")
    @Expose
    private List<String> cityId = null;
    @SerializedName("donation_last_date")
    @Expose
    private List<String> donationLastDate = null;
    @SerializedName("blood_type")
    @Expose
    private List<String> bloodType = null;
    @SerializedName("password")
    @Expose
    private List<String> password = null;
    @SerializedName("email")
    @Expose
    private List<String> email = null;

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<String> getCityId() {
        return cityId;
    }

    public void setCityId(List<String> cityId) {
        this.cityId = cityId;
    }

    public List<String> getDonationLastDate() {
        return donationLastDate;
    }

    public void setDonationLastDate(List<String> donationLastDate) {
        this.donationLastDate = donationLastDate;
    }

    public List<String> getBloodType() {
        return bloodType;
    }

    public void setBloodType(List<String> bloodType) {
        this.bloodType = bloodType;
    }

    public List<String> getPassword() {
        return password;
    }

    public void setPassword(List<String> password) {
        this.password = password;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public User_Information getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(User_Information userinfo) {
        this.userinfo = userinfo;
    }

    @SerializedName("userinfo")
    @Expose
    private User_Information userinfo;

}
