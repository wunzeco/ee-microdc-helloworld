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

    private final static String PROJECT_TOKEN = "cd865237e1c9f06d8bbfc20666926b3b";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private final MessageBuilder messageBuilder;
    private final ClientDelivery clientDelivery;
    private final MixpanelAPI mixpanelAPI;

    private final Notification notification;
    private final NotificationType notificationType;
    private final boolean suppressSend;

    public NotificationService(Notification notification, NotificationType theNotificationType, boolean suppressSend) {
        this.suppressSend = suppressSend;
        messageBuilder = new MessageBuilder(PROJECT_TOKEN);
        clientDelivery = new ClientDelivery();
        mixpanelAPI = new MixpanelAPI();

        this.notification = notification;
        notificationType = theNotificationType;
    }

    public void send() {
        if (!suppressSend) doSend();
    }

    void doSend() {
        try {
            final CustomerProfile profile = notification.profile;
            final NotificationDetails details = notification.details;

            final JSONObject properties = new JSONObject();
            properties.put("notificationType", notificationType);
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

    public NotificationType getNotificationType() {
        return notificationType;
    }
}
