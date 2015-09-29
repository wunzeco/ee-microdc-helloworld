package com.barclaycard.collections.controllers;

import com.barclaycard.collections.model.CustomerProfile;
import com.barclaycard.collections.model.Notification;
import com.barclaycard.collections.model.NotificationDetails;
import com.barclaycard.collections.system.NotificationMethod;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class NotificationDecisionControllerTest {

    NotificationDecisionController controller = new NotificationDecisionController(true);
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).setValidator(new LocalValidatorFactoryBean()).build();

    @Test
    public void sendNotification_shouldReturnNoContent() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CustomerProfile customerProfile = new CustomerProfile("CNO01", "bob", "C", "1", "bob@bob.com", "07123123456", "email");
        NotificationDetails notificationDetails =
                new NotificationDetails(NotificationDetails.NotificationOrigin.customer, NotificationMethod.email, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "Some text");
        Notification notification = new Notification(customerProfile, notificationDetails);
        mockMvc.perform(post("/sendNotifications")
                .content(mapper.writeValueAsBytes(notification))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
