package com.barclaycard.collections.controllers;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class HelloControllerTest {

    HelloController controller = new HelloController("Hello");
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).setValidator(new LocalValidatorFactoryBean()).build();

    @Test
    public void sayHello_shouldGreetTheUser() throws Exception {
        mockMvc.perform(get("/sayHello"))
                .andExpect(model().attribute("greeting", "Hello"))
                .andExpect(model().attribute("name", "Joe"))
                .andExpect(view().name("hello"));
    }

    @Test
    public void sayHello_shouldGreetTheUserByName_givenANameIsSupplied() throws Exception {
        mockMvc.perform(get("/sayHello?name=Ben"))
                .andExpect(model().attribute("greeting", "Hello"))
                .andExpect(model().attribute("name", "Ben"))
                .andExpect(view().name("hello"));
    }


}