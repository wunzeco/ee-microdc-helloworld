package com.barclaycard.collections.controllers;

import com.barclaycard.collections.model.CustomerProfile;
import com.barclaycard.collections.system.NotificationService;
import com.barclaycard.collections.system.CollectionService;
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
        CollectionService collectionService = new CollectionService(profile, suppressNotifications);
        collectionService.doNotify();

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/notifications", method = RequestMethod.GET)
    public
    @ResponseBody
    List<NotificationService> getNotifications() {
        List<NotificationService> deliveredNotifications = CollectionService.getNotifications();

        return deliveredNotifications;
    }

    @RequestMapping(value = "/notifications/{customerId}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<NotificationService> getNotificationsForCustomer(@PathVariable("customerId") String customerId) {
        List<NotificationService> deliveredNotifications = CollectionService.getNotifications(customerId);

        return deliveredNotifications;
    }
}
