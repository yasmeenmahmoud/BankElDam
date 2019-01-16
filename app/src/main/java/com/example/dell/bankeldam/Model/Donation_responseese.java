package com.example.dell.bankeldam.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Donation_responseese {

        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("msg")
        @Expose
        private String msg;
        @SerializedName("data")
        @Expose
        private DonationRequest_Response data;


//    @SerializedName("data")
//    @Expose
//    private DonationRequest_Data detailsData;
//
//
//
//
//    public DonationRequest_Data getDetailsData() {
//        return detailsData;
//    }
//
//    public void setDetailsData(DonationRequest_Data detailsData) {
//        this.detailsData = detailsData;
//    }





        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public DonationRequest_Response getData() {
            return data;
        }
        public void setData(DonationRequest_Response data) {
            this.data = data;
        }

    }
