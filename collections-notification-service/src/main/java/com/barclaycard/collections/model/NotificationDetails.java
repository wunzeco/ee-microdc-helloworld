package com.barclaycard.collections.model;

public class NotificationDetails {
    public static enum NotificationOrigin {
        customer,
        barclaycard
    }

    public NotificationOrigin from;
    public String time;
    public String text;

    public NotificationDetails() {
    }

    public NotificationDetails(String from, String time, String text) {
        this.from = NotificationOrigin.valueOf(from);
        this.time = time;
        this.text = text;
    }

    public NotificationDetails(NotificationOrigin from, String time, String text) {
        this.from = from;
        this.time = time;
        this.text = text;
    }

    @Override
    public String toString() {
        return "NotificationDetails{" +
                "from=" + from +
                ", time='" + time + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
