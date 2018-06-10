package com.ayadykin.ggstars.test.library.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import com.ayadykin.ggstars.test.library.Application;
import com.ayadykin.ggstars.test.library.dto.AuthorDto;
import com.ayadykin.ggstars.test.library.init.Init;
import com.ayadykin.ggstars.test.library.utils.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class AuthorControllerTest extends GeneralControllerTest {

	@Test
	public void getAuthorByIdTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/author/1").with(postProcessor)).andExpect(status().isOk()).andReturn();

		AuthorDto authorDto = objectMapper.readValue(result.getResponse().getContentAsString(), AuthorDto.class);

		assertEquals(Init.AUTHOR1_FIRST_NAME, authorDto.getFirstName());
		assertEquals(Init.AUTHOR1_LAST_NAME, authorDto.getLastName());
		assertEquals(Init.AUTHOR1_BIRTHDAY, authorDto.getBirthDate().format(DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)));
		assertFalse(authorDto.getBooks().isEmpty());
		assertEquals(Init.BOOK1_TITLE, authorDto.getBooks().get(0).getTitle());
		assertEquals(Init.BOOK1_ISBN, authorDto.getBooks().get(0).getIsbn());
	}

	@Test
	public void getAuthorShortInfoTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/author/info/short/1").with(postProcessor)).andExpect(status().isOk()).andReturn();

		AuthorDto authorDto = objectMapper.readValue(result.getResponse().getContentAsString(), AuthorDto.class);

		assertEquals(Init.AUTHOR1_FIRST_NAME, authorDto.getFirstName());
		assertEquals(Init.AUTHOR1_LAST_NAME, authorDto.getLastName());
		assertFalse(authorDto.getBooks().isEmpty());
		assertEquals(Init.BOOK1_TITLE, authorDto.getBooks().get(0).getTitle());
	}
}
