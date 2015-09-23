package com.barclaycard.collections.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotificationDecisionController {
    private final String greeting;

    public NotificationDecisionController(String greeting) {
        this.greeting = greeting;
    }

    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public String sayHello(@RequestParam(value = "name", defaultValue = "Joe") String name, Model model) {
        model.addAttribute("greeting", greeting);
        model.addAttribute("name", name);
        return "hello";
    }
}
