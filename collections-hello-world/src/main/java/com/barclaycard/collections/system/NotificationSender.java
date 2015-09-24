package com.barclaycard.collections.system;

import com.barclaycard.collections.model.CustomerProfile;
import com.mixpanel.mixpanelapi.ClientDelivery;
import com.mixpanel.mixpanelapi.MessageBuilder;
import com.mixpanel.mixpanelapi.MixpanelAPI;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class NotificationSender {

    private final static String PROJECT_TOKEN = "f346f7b1fb0a69969724710bc6963cdc";

    private final MessageBuilder messageBuilder;
    private final ClientDelivery clientDelivery;
    private final MixpanelAPI mixpanelAPI;

    private final CustomerProfile customerProfile;
    private final NotificationType notificationType;
    private final boolean suppressSend;

    public NotificationSender(CustomerProfile customerProfile, NotificationType theNotificationType, boolean suppressSend){
        this.suppressSend = suppressSend;
        messageBuilder = new MessageBuilder(PROJECT_TOKEN);
        clientDelivery = new ClientDelivery();
        mixpanelAPI = new MixpanelAPI();

        this.customerProfile = customerProfile;
        notificationType = theNotificationType;
    }

    public void send() {
        if(!suppressSend) doSend();
    }

    void doSend() {
        try {
            final JSONObject properties = new JSONObject();
            properties.put("notificationType", notificationType);
            clientDelivery.addMessage(messageBuilder.event(customerProfile.customerId, "NotificationTestEvent", properties));
            mixpanelAPI.deliver(clientDelivery);
        } catch (IOException|JSONException e) {
            e.printStackTrace();
        }
    }

    public CustomerProfile getCustomerProfile() {
        return customerProfile;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }
}
