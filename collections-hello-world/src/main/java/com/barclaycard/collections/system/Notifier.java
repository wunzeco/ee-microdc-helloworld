package com.barclaycard.collections.system;

import com.barclaycard.collections.model.CustomerProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Notifier {
    private final static List<NotificationSender> notificationDeliveries = new ArrayList<>();

    private final CustomerProfile customerProfile;
    private final Boolean suppressNotifications;
    private final CustomerNotificationStrategy customerNotificationStrategy;

    public Notifier(CustomerProfile theCustomerProfile, Boolean suppressNotifications) {
        customerProfile = theCustomerProfile;
        customerNotificationStrategy = new CustomerNotificationStrategy(customerProfile);

        this.suppressNotifications = suppressNotifications;
    }

    public void doNotify() {
        final NotificationSender notificationSender =
                new NotificationSender(customerProfile, customerNotificationStrategy.getNotificationType(), suppressNotifications);
        notificationSender.send();
        notificationDeliveries.add(notificationSender);
    }

    public static List<NotificationSender> getNotifications() {
        return notificationDeliveries;
    }

    public static List<NotificationSender> getNotifications(String customerId) {
        return notificationDeliveries.stream()
                .filter(nd -> nd.getCustomerProfile().customerId.equals(customerId))
                .collect(Collectors.toList());
    }
}

