package com.barclaycard.collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HelloWorldApplication.class)
@WebAppConfiguration
public class HelloWorldApplicationTests {

	@Autowired
	WebApplicationContext ctx;
	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	@Test
	public void controllerEndpointsAreAccessible() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get(UrlFixtures.DYNAMIC_URL)).andExpect(status().isOk());
	}

	@Test
	public void staticData_isAccessible() throws Exception {
	    mvc.perform(MockMvcRequestBuilders.get(UrlFixtures.STATIC_URL)).andExpect(status().isOk());
	}
}