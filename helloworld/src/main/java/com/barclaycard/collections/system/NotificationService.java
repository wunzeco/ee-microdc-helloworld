package com.barclaycard.collections.system;

import com.barclaycard.collections.model.CustomerProfile;
import com.barclaycard.collections.model.Notification;
import com.barclaycard.collections.model.NotificationDetails;
import com.mixpanel.mixpanelapi.ClientDelivery;
import com.mixpanel.mixpanelapi.MessageBuilder;
import com.mixpanel.mixpanelapi.MixpanelAPI;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


public class NotificationService {

    private final static String PROJECT_TOKEN = "9e7f073b4605de653b0814e5d9ad48d6";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private final MessageBuilder messageBuilder;
    private final ClientDelivery clientDelivery;
    private final MixpanelAPI mixpanelAPI;

    private final Notification notification;
    private final NotificationMethod notificationMethod;
    private final boolean suppressSend;

    public NotificationService(Notification notification, NotificationMethod notificationMethod, boolean suppressSend) {
        this.suppressSend = suppressSend;
        messageBuilder = new MessageBuilder(PROJECT_TOKEN);
        clientDelivery = new ClientDelivery();
        mixpanelAPI = new MixpanelAPI();

        this.notification = notification;
        this.notificationMethod = notificationMethod;
    }

    public void send() {
        if (!suppressSend) doSend();
    }

    void doSend() {
        try {
            final CustomerProfile profile = notification.profile;
            final NotificationDetails details = notification.details;

            final JSONObject properties = new JSONObject();
            properties.put("notificationMethod", notificationMethod);
            properties.put("text", details.text);
            if (details.time != null) {
                final LocalDateTime localDateTime = LocalDateTime.parse(details.time, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
                properties.put("time", String.valueOf(localDateTime.toEpochSecond(ZoneOffset.UTC)));
            }
            clientDelivery.addMessage(messageBuilder.event(notification.profile.customerId, "NotificationTestEvent", properties));
            mixpanelAPI.deliver(clientDelivery);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public Notification getNotification() {
        return notification;
    }

    public NotificationMethod getNotificationMethod() {
        return notificationMethod;
    }
}
