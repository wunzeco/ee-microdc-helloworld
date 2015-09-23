package com.barclaycard.collections.controllers;

import com.barclaycard.collections.model.CustomerProfile;
import com.barclaycard.collections.system.NotificationDeliverer;
import com.barclaycard.collections.system.Notifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NotificationDecisionController {

    @RequestMapping(value = "/sendNotifications", method = RequestMethod.POST)
    public ResponseEntity<Void> sendNotifications(@RequestBody CustomerProfile profile) {
        Notifier notifier = new Notifier();
        NotificationDeliverer notificationDeliverer = notifier.customerNotification(profile);
        notificationDeliverer.send();

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
