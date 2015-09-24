package com.barclaycard.collections.system;

import com.barclaycard.collections.model.CustomerProfile;

import static org.springframework.util.StringUtils.isEmpty;

public class CustomerNotification {
    private final CustomerProfile customerProfile;

    public CustomerNotification(final CustomerProfile theCustomerProfile) {

        this.customerProfile = theCustomerProfile;
    }

    public  Notification getNotification() {
        if(customerProfile.contactPreference == null) {
            if (!isEmpty(customerProfile.email)) return Notification.Email;
            else if (!isEmpty(customerProfile.mobile)) return Notification.SMS;
        } else {
            switch (customerProfile.contactPreference) {
                case "email":
                    if (!isEmpty(customerProfile.email)) return Notification.Email;
                case "mobile":
                    if (!isEmpty(customerProfile.mobile)) return Notification.SMS;
                default:
                    if (!isEmpty(customerProfile.email)) return Notification.Email;
                    else if (!isEmpty(customerProfile.mobile)) return Notification.SMS;
            }
        }

        return Notification.Letter;
    }
}
