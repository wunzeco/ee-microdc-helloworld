package com.barclaycard.collections.controllers;

import com.barclaycard.collections.model.CustomerProfile;
import com.barclaycard.collections.system.NotificationSender;
import com.barclaycard.collections.system.Notifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NotificationDecisionController {
    private final Boolean suppressNotifications;

    public NotificationDecisionController(Boolean suppressNotifications) {
        this.suppressNotifications = suppressNotifications;
    }

    @RequestMapping(value = "/sendNotifications", method = RequestMethod.POST)
    public ResponseEntity<Void> sendNotifications(@RequestBody CustomerProfile profile) {
        Notifier notifier = new Notifier(profile, suppressNotifications);
        notifier.doNotify();

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/notifications", method = RequestMethod.GET)
    public
    @ResponseBody
    List<NotificationSender> getNotifications() {
        List<NotificationSender> deliveredNotifications = Notifier.getNotifications();

        return deliveredNotifications;
    }

    @RequestMapping(value = "/notifications/{customerId}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<NotificationSender> getNotificationsForCustomer(@PathVariable("customerId") String customerId) {
        List<NotificationSender> deliveredNotifications = Notifier.getNotifications(customerId);

        return deliveredNotifications;
    }
}
