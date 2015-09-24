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
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class NotificationDecisionController {

    @RequestMapping(value = "/sendNotifications", method = RequestMethod.POST)
    public ResponseEntity<Void> sendNotifications(@RequestBody CustomerProfile profile) {
        Notifier notifier = new Notifier(profile);
        notifier.doNotify();

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/notifications", method = RequestMethod.GET)
    public
    @ResponseBody
    List<NotificationDeliverer> getNotifications() {

        return Notifier.getNotifications();
    }
}
