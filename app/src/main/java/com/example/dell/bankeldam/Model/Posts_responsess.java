package com.example.dell.bankeldam.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Posts_responsess {

        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("msg")
        @Expose
        private String msg;
        @SerializedName("data")
        @Expose
        private Postes_Response data;

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

        public Postes_Response getData() {
            return data;
        }

        public void setData(Postes_Response data) {
            this.data = data;
        }

    }

