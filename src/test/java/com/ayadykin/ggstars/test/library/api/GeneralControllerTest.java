package com.ayadykin.ggstars.test.library.api;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GeneralControllerTest {

	private final static String TEST_USER = "user";
    private final static String TEST_PASSWORD = "user_password";
    
	@Autowired
	private WebApplicationContext context;
	@Autowired
	private FilterChainProxy filterChainProxy;
	
	protected MockMvc mockMvc;
	@Autowired
	protected ObjectMapper objectMapper;
	protected RequestPostProcessor postProcessor = httpBasic(TEST_USER, TEST_PASSWORD);
	
	@Before
	public void generalControllerTest() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilters(filterChainProxy).apply(springSecurity()).build();
	}

}
