package com.barclaycard.collections.system;

import com.barclaycard.collections.model.CustomerProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionService {
    private final static List<NotificationService> notificationDeliveries = new ArrayList<>();

    private final CustomerProfile customerProfile;
    private final Boolean suppressNotifications;
    private final CustomerNotificationStrategy customerNotificationStrategy;

    public CollectionService(CustomerProfile theCustomerProfile, Boolean suppressNotifications) {
        customerProfile = theCustomerProfile;
        customerNotificationStrategy = new CustomerNotificationStrategy(customerProfile);

        this.suppressNotifications = suppressNotifications;
    }

    public void doNotify() {
        final NotificationService notificationService =
                new NotificationService(customerProfile, customerNotificationStrategy.getNotificationType(), suppressNotifications);
        notificationService.send();
        notificationDeliveries.add(notificationService);
    }

    public static List<NotificationService> getNotifications() {
        return notificationDeliveries;
    }

    public static List<NotificationService> getNotifications(String customerId) {
        return notificationDeliveries.stream()
                .filter(nd -> nd.getCustomerProfile().customerId.equals(customerId))
                .collect(Collectors.toList());
    }
}

