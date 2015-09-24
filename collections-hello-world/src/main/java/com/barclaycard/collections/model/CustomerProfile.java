package com.barclaycard.collections.model;

public class CustomerProfile {
    public String customerId;
    public String accountStatus;
    public String cycle;
    public String email;
    public String mobile;
    public String contactPreference;
    public String time;

    public CustomerProfile() {
    }

    public CustomerProfile(String customerId, String accountStatus, String cycle, String email, String mobile, String contactPreference) {
        this(customerId, accountStatus, cycle, email, mobile, contactPreference, null);
    }

    public CustomerProfile(String customerId, String accountStatus, String cycle, String email, String mobile, String contactPreference, String time) {
        this.customerId = customerId;
        this.accountStatus = accountStatus;
        this.cycle = cycle;
        this.email = email;
        this.mobile = mobile;
        this.contactPreference = contactPreference;
        this.time = time;
    }
}
