package com.barclaycard.collections.model;

public class CustomerProfile {
    public String customerId;
    public String accountStatus;
    public String cycle;
    public String email;
    public String mobile;
    public String contactPreference;

    public CustomerProfile() {
    }

    public CustomerProfile(String customerId, String accountStatus, String cycle, String email, String mobile, String contactPreference) {
        this.customerId = customerId;
        this.accountStatus = accountStatus;
        this.cycle = cycle;
        this.email = email;
        this.mobile = mobile;
        this.contactPreference = contactPreference;
    }

    @Override
    public String toString() {
        return "CustomerProfile{" +
                "customerId='" + customerId + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                ", cycle='" + cycle + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", contactPreference='" + contactPreference + '\'' +
                '}';
    }
}
