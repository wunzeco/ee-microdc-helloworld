package com.barclaycard.collections.system;

import com.barclaycard.collections.model.CustomerProfile;

public class Notifier {
    public NotificationDeliverer customerNotification(CustomerProfile profile) {
        return NotificationDeliverer.forCustomer(profile);
    }

}

