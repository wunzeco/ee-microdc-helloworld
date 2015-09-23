package com.barclaycard.collections;

import com.barclaycard.collections.controllers.NotificationDecisionController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class Controllers extends WebMvcConfigurerAdapter {

    @Bean
    public NotificationDecisionController notificationDecisionController() {
        return new NotificationDecisionController();
    }
}
