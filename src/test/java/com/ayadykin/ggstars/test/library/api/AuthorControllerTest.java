package com.ayadykin.ggstars.test.library.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import com.ayadykin.ggstars.test.library.Application;
import com.ayadykin.ggstars.test.library.dto.AuthorDto;
import com.ayadykin.ggstars.test.library.entity.enums.Sex;
import com.ayadykin.ggstars.test.library.init.Init;
import com.ayadykin.ggstars.test.library.utils.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class AuthorControllerTest extends GeneralControllerTest {

	public static final String TEST_AUTHOR1_FIRST_NAME = "Alexander";
	public static final String TEST_AUTHOR1_LAST_NAME = "Pushkin";
	public static final String TEST_AUTHOR1_BIRTHDAY = "26.05.1799";
	public static final String TEST_UPDATE_FIRST_NAME = "Alex";
	public static final String TEST_UPDATE_LAST_NAME = "Ахматова";
	public static final String TEST_UPDATE_BIRTHDAY = "13.12.1928";

	@Test
	public void getAuthorByIdTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/author/1").with(postProcessor)).andExpect(status().isOk()).andReturn();

		AuthorDto authorDto = objectMapper.readValue(result.getResponse().getContentAsString(), AuthorDto.class);

		assertNotNull(authorDto);
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

		assertNotNull(authorDto);
		assertEquals(Init.AUTHOR1_FIRST_NAME, authorDto.getFirstName());
		assertEquals(Init.AUTHOR1_LAST_NAME, authorDto.getLastName());
		assertFalse(authorDto.getBooks().isEmpty());
		assertEquals(Init.BOOK1_TITLE, authorDto.getBooks().get(0).getTitle());
	}

	@Test
	public void createAuthorTest() throws Exception {
		AuthorDto authorDto = new AuthorDto();
		authorDto.setFirstName(TEST_AUTHOR1_FIRST_NAME);
		authorDto.setLastName(TEST_AUTHOR1_LAST_NAME);
		authorDto.setSex(Sex.MALE);
		authorDto.setBirthDate(LocalDate.parse(TEST_AUTHOR1_BIRTHDAY, DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)));

		String authorJson = objectMapper.writeValueAsString(authorDto);
		MvcResult result = mockMvc.perform(post("/author").content(authorJson).contentType(MediaType.APPLICATION_JSON).with(postProcessor))
				.andExpect(status().isOk()).andReturn();

		authorDto = objectMapper.readValue(result.getResponse().getContentAsString(), AuthorDto.class);

		assertNotNull(authorDto);
		assertTrue(authorDto.getId() > 0);
		assertEquals(TEST_AUTHOR1_FIRST_NAME, authorDto.getFirstName());
		assertEquals(TEST_AUTHOR1_LAST_NAME, authorDto.getLastName());
		assertEquals(TEST_AUTHOR1_BIRTHDAY, authorDto.getBirthDate().format(DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)));
		assertEquals(Sex.MALE, authorDto.getSex());
	}

	@Test
	public void updateAuthorTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/author/3").with(postProcessor)).andExpect(status().isOk()).andReturn();

		AuthorDto authorDto = objectMapper.readValue(result.getResponse().getContentAsString(), AuthorDto.class);
		authorDto.setFirstName(TEST_UPDATE_FIRST_NAME);

		String authorJson = objectMapper.writeValueAsString(authorDto);
		result = mockMvc.perform(put("/author").content(authorJson).contentType(MediaType.APPLICATION_JSON).with(postProcessor))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(authorDto);
		assertEquals(TEST_UPDATE_FIRST_NAME, authorDto.getFirstName());
		assertEquals(TEST_UPDATE_LAST_NAME, authorDto.getLastName());
		assertEquals(TEST_UPDATE_BIRTHDAY, authorDto.getBirthDate().format(DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)));
		assertEquals(Sex.FEMALE, authorDto.getSex());
	}
}
