package com.barclaycard.collections.model;

public class Notification {
    public CustomerProfile profile;
    public NotificationDetails details;

    public Notification() {
    }

    public Notification(CustomerProfile profile, NotificationDetails details) {
        this.profile = profile;
        this.details = details;
    }

    public void setProfile(CustomerProfile profile) {
        this.profile = profile;
    }

    public void setDetails(NotificationDetails details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "profile=" + profile +
                ", details=" + details +
                '}';
    }
}
