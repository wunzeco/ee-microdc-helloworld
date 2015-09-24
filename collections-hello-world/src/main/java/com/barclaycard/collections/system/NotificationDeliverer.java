package com.barclaycard.collections.system;

import com.barclaycard.collections.model.CustomerProfile;
import com.mixpanel.mixpanelapi.ClientDelivery;
import com.mixpanel.mixpanelapi.MessageBuilder;
import com.mixpanel.mixpanelapi.MixpanelAPI;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class NotificationDeliverer {

    public static NotificationDeliverer forCustomer(final CustomerProfile customerProfile) {
        CustomerNotification customerNotification = new CustomerNotification(customerProfile);
        return new NotificationDeliverer(customerProfile, customerNotification.getNotification());
    }

    private final static String PROJECT_TOKEN = "f346f7b1fb0a69969724710bc6963cdc";

    private final MessageBuilder messageBuilder;
    private final ClientDelivery clientDelivery;
    private final MixpanelAPI mixpanelAPI;

    private final CustomerProfile customerProfile;
    private final Notification notification;

    private NotificationDeliverer(CustomerProfile customerProfile, Notification theNotification){
        messageBuilder = new MessageBuilder(PROJECT_TOKEN);
        clientDelivery = new ClientDelivery();
        mixpanelAPI = new MixpanelAPI();

        this.customerProfile = customerProfile;
        notification = theNotification;
    }

    public void send() {
        try {
            final JSONObject properties = new JSONObject();
            properties.put("notificationType", notification);
            clientDelivery.addMessage(messageBuilder.event(customerProfile.customerId, "NotificationTestEvent", properties));
            mixpanelAPI.deliver(clientDelivery);
        } catch (IOException|JSONException e) {
            e.printStackTrace();
        }
    }

    public CustomerProfile getCustomerProfile() {
        return customerProfile;
    }

    public Notification getNotification() {
        return notification;
    }
}
