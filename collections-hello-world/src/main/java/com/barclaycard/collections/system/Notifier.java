package com.barclaycard.collections.system;

import com.barclaycard.collections.model.CustomerProfile;

import java.util.ArrayList;
import java.util.List;

public class Notifier {
    private final static List<NotificationDeliverer> notificationDeliveries = new ArrayList<>();

    private final CustomerProfile customerProfile;

    public Notifier(CustomerProfile theCustomerProfile) {
        customerProfile = theCustomerProfile;
    }

    public void doNotify() {
        final NotificationDeliverer notificationDeliverer = NotificationDeliverer.forCustomer(customerProfile);
        notificationDeliverer.send();
        notificationDeliveries.add(notificationDeliverer);
    }

    public static List<NotificationDeliverer> getNotifications() {
        return notificationDeliveries;
    }
}

