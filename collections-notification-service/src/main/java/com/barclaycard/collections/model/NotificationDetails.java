package com.barclaycard.collections.model;

public class NotificationDetails {
    public String time;
    public String text;

    public NotificationDetails() {
    }

    public NotificationDetails(String time, String text) {
        this.time = time;
        this.text = text;
    }

    @Override
    public String toString() {
        return "NotificationDetails{" +
                "time=" + time +
                ", text='" + text + '\'' +
                '}';
    }
}
