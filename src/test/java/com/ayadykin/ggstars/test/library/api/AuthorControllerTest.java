package com.ayadykin.ggstars.test.library.api;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import com.ayadykin.ggstars.test.library.Application;
import com.ayadykin.ggstars.test.library.config.WebConfiguration;
import com.ayadykin.ggstars.test.library.dto.AuthorDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { WebConfiguration.class, Application.class })
public class AuthorControllerTest extends GeneralControllerTest {

	@Test
	public void getBookTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/author/1").with(postProcessor)).andExpect(status().isOk()).andReturn();

		AuthorDto authorDto = objectMapper.readValue(result.getResponse().getContentAsString(), AuthorDto.class);
		assertEquals("Alexander", authorDto.getFirstName());
	}
}
