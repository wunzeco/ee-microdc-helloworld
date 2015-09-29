package com.barclaycard.collections.system;

import com.barclaycard.collections.model.CustomerProfile;

import static org.springframework.util.StringUtils.isEmpty;

public class CustomerNotificationStrategy {
    private final CustomerProfile customerProfile;

    public CustomerNotificationStrategy(final CustomerProfile customerProfile) {
        this.customerProfile = customerProfile;
    }

    public NotificationType getNotificationType() {
        if (customerProfile.contactPreference == null) {
            if (!isEmpty(customerProfile.email)) return NotificationType.Email;
            else if (!isEmpty(customerProfile.mobile)) return NotificationType.SMS;
        } else {
            switch (customerProfile.contactPreference) {
                case "email":
                    if (!isEmpty(customerProfile.email)) return NotificationType.Email;
                case "mobile":
                    if (!isEmpty(customerProfile.mobile)) return NotificationType.SMS;
                default:
                    if (!isEmpty(customerProfile.email)) return NotificationType.Email;
                    else if (!isEmpty(customerProfile.mobile)) return NotificationType.SMS;
            }
        }

        return NotificationType.Letter;
    }
}
