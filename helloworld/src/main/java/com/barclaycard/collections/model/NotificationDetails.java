package com.barclaycard.collections.model;

import com.barclaycard.collections.system.NotificationMethod;

public class NotificationDetails {
    public static enum NotificationOrigin {
        customer,
        barclaycard
    }

    public NotificationOrigin from;
    public NotificationMethod method;
    public String time;
    public String text;

    public NotificationDetails() {
    }

    public NotificationDetails(String from, String method, String time, String text) {
        this.from = NotificationOrigin.valueOf(from);
        this.method = NotificationMethod.valueOf(method);
        this.time = time;
        this.text = text;
    }

    public NotificationDetails(NotificationOrigin from, NotificationMethod method, String time, String text) {
        this.from = from;
        this.method = method;
        this.time = time;
        this.text = text;
    }

    @Override
    public String toString() {
        return "NotificationDetails{" +
                "from=" + from +
                ", method='" + method + '\'' +
                ", time='" + time + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
