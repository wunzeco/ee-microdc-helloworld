package com.barclaycard.collections.system;

import com.barclaycard.collections.model.CustomerProfile;
import com.mixpanel.mixpanelapi.ClientDelivery;
import com.mixpanel.mixpanelapi.MessageBuilder;
import com.mixpanel.mixpanelapi.MixpanelAPI;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class NotificationService {

    private final static String PROJECT_TOKEN = "cd865237e1c9f06d8bbfc20666926b3b";

    private final MessageBuilder messageBuilder;
    private final ClientDelivery clientDelivery;
    private final MixpanelAPI mixpanelAPI;

    private final CustomerProfile customerProfile;
    private final NotificationType notificationType;
    private final boolean suppressSend;

    public NotificationService(CustomerProfile customerProfile, NotificationType theNotificationType, boolean suppressSend) {
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
