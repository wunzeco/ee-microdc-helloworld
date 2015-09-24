package com.barclaycard.collections.controllers;

import com.barclaycard.collections.model.CustomerProfile;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class NotificationDecisionControllerTest {

    NotificationDecisionController controller = new NotificationDecisionController(true);
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).setValidator(new LocalValidatorFactoryBean()).build();

    @Test
    public void sendNotification_shouldReturnNoContent() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CustomerProfile customerProfile = new CustomerProfile("bob", "H", "bob@bob.com", "07123123456", "email");
        mockMvc.perform(post("/sendNotifications")
                .content(mapper.writeValueAsBytes(customerProfile))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
