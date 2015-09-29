package com.barclaycard.collections.system;

import com.barclaycard.collections.model.CustomerProfile;

import static org.springframework.util.StringUtils.isEmpty;

public class CustomerNotificationStrategy {
    private final CustomerProfile customerProfile;

    public CustomerNotificationStrategy(final CustomerProfile customerProfile) {
        this.customerProfile = customerProfile;
    }

    public NotificationMethod getNotificationMethod() {
        if (customerProfile.contactPreference == null) {
            if (!isEmpty(customerProfile.email)) return NotificationMethod.email;
            else if (!isEmpty(customerProfile.mobile)) return NotificationMethod.sms;
        } else {
            switch (customerProfile.contactPreference) {
                case "email":
                    if (!isEmpty(customerProfile.email)) return NotificationMethod.email;
                case "sms":
                    if (!isEmpty(customerProfile.mobile)) return NotificationMethod.sms;
                case "voice":
                    if (!isEmpty(customerProfile.mobile)) return NotificationMethod.voice;
                default:
                    if (!isEmpty(customerProfile.email)) return NotificationMethod.email;
                    else if (!isEmpty(customerProfile.mobile)) return NotificationMethod.sms;
            }
        }

        return NotificationMethod.letter;
    }
}
