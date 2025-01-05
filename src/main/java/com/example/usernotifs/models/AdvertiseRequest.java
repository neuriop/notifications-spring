package com.example.usernotifs.models;

public class AdvertiseRequest {
    private int[] userid;
    private String message;

    public AdvertiseRequest(int[] userid, String message) {
        this.userid = userid;
        this.message = message;
    }

    public int[] getUserid() {
        return userid;
    }

    public String getMessage() {
        return message;
    }
}
