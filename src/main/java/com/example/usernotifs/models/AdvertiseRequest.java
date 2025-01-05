package com.example.usernotifs.models;

public class AdvertiseRequest {
    private int[] userid;
    private int courseid;

    public AdvertiseRequest(int[] userid, int courseid) {
        this.userid = userid;
        this.courseid = courseid;
    }

    public int[] getUserid() {
        return userid;
    }

    public int getCourseid() {
        return courseid;
    }
}
