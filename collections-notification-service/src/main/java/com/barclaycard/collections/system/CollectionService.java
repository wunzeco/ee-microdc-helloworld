package com.barclaycard.collections.system;

import com.barclaycard.collections.model.Notification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionService {
    private final static List<NotificationService> notificationDeliveries = new ArrayList<>();

    private final Notification notification;
    private final Boolean suppressNotifications;
    private final CustomerNotificationStrategy customerNotificationStrategy;

    public CollectionService(Notification theNotification, Boolean suppressNotifications) {
        notification = theNotification;
        customerNotificationStrategy = new CustomerNotificationStrategy(notification.profile);

        this.suppressNotifications = suppressNotifications;
    }

    public static List<NotificationService> getNotifications() {
        return notificationDeliveries;
    }

    public static List<NotificationService> getNotifications(String customerId) {
        return notificationDeliveries.stream()
                .filter(nd -> nd.getNotification().profile.customerId.equals(customerId))
                .collect(Collectors.toList());
    }

    public void doNotify() {
        final NotificationService notificationService =
                new NotificationService(notification, customerNotificationStrategy.getNotificationMethod(), suppressNotifications);
        notificationService.send();
        notificationDeliveries.add(notificationService);
    }
}

