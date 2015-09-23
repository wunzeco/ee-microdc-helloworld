package com.barclaycard.collections.model;

public class CustomerProfile {
    public String customerId;
    public String riskRating;
    public String email;
    public String mobile;
    public String contactPreference;

    public CustomerProfile() {
    }

    public CustomerProfile(String customerId, String riskRating, String email, String mobile, String contactPreference) {
        this.customerId = customerId;
        this.riskRating = riskRating;
        this.email = email;
        this.mobile = mobile;
        this.contactPreference = contactPreference;
    }
}
