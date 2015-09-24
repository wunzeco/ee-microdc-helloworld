package com.barclaycard.collections;

import com.barclaycard.collections.model.CustomerProfile;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NotificationDecisionService.class)
@WebAppConfiguration
public class NotificationDecisionServiceTests {

	@Autowired
	WebApplicationContext ctx;
	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Test
	public void controllerEndpointsAreAccessible() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		CustomerProfile customerProfile = new CustomerProfile("bob", "C", "1", "bob@bob.com", "07123123456", "email");
		mvc.perform(MockMvcRequestBuilders.post(UrlFixtures.DYNAMIC_URL)
		.content(mapper.writeValueAsBytes(customerProfile))
		.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}
}
