package com.example.usernotifs.models;

import java.util.HashMap;
import java.util.Map;

public class Notification {
    private int userid;
    private Map<Integer, String> notificationMessages;

    public Notification(int userid) {
        this.userid = userid;
        this.notificationMessages = new HashMap<>();
    }

    public int getUserid() {
        return userid;
    }

    public Map<Integer, String> getNotificationMessages() {
        return notificationMessages;
    }

    public void putMessage(String notification) {
        notificationMessages.put(notificationMessages.size(), notification);
    }
}
