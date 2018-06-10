package com.ayadykin.ggstars.test.library.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;

import com.ayadykin.ggstars.test.library.Application;
import com.ayadykin.ggstars.test.library.dto.BookDto;
import com.ayadykin.ggstars.test.library.dto.ErrorDto;
import com.ayadykin.ggstars.test.library.entity.enums.Genre;
import com.ayadykin.ggstars.test.library.exception.LibraryException;
import com.ayadykin.ggstars.test.library.init.Init;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { Application.class })
public class BookControllerTest extends GeneralControllerTest {

	public static final String TEST_BOOK1_TITLE = "Test book title";
	public static final String TEST_BOOK1_ISBN = "Test book isbn " + System.currentTimeMillis();
	public static final String TEST_UPDATE_TITLE = "Updated book title";
	public static final String TEST_UPDATE_ISBN = "123458";

	@Test
	public void getBookTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/book/1").with(postProcessor)).andExpect(status().isOk()).andReturn();

		BookDto bookDto = objectMapper.readValue(result.getResponse().getContentAsString(), BookDto.class);
		assertEquals(Init.BOOK1_TITLE, bookDto.getTitle());
		assertEquals(Init.BOOK1_ISBN, bookDto.getIsbn());
		assertFalse(bookDto.getAuthors().isEmpty());
		assertEquals(Init.AUTHOR1_FIRST_NAME, bookDto.getAuthors().get(0).getFirstName());
		assertEquals(Init.AUTHOR1_LAST_NAME, bookDto.getAuthors().get(0).getLastName());
	}

	@Test
	public void errorGetBookTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/book/1000").with(postProcessor)).andExpect(status().is4xxClientError()).andReturn();

		ErrorDto expectedErrorDto = new ErrorDto(LibraryException.EMPTY_BOOK);
		assertEquals(objectMapper.writeValueAsString(expectedErrorDto), result.getResponse().getContentAsString());
	}

	@Test
	public void createBookTest() throws Exception {
		BookDto bookDto = new BookDto();
		bookDto.setTitle(TEST_BOOK1_TITLE);
		bookDto.setIsbn(TEST_BOOK1_ISBN);
		bookDto.setGenre(Genre.NOVEL);

		String bookJson = objectMapper.writeValueAsString(bookDto);
		MvcResult result = mockMvc.perform(post("/book").content(bookJson).contentType(MediaType.APPLICATION_JSON).with(postProcessor))
				.andExpect(status().isOk()).andReturn();

		bookDto = objectMapper.readValue(result.getResponse().getContentAsString(), BookDto.class);

		assertNotNull(bookDto);
		assertTrue(bookDto.getId() > 0);
		assertEquals(TEST_BOOK1_TITLE, bookDto.getTitle());
		assertEquals(TEST_BOOK1_ISBN, bookDto.getIsbn());
		assertEquals(Genre.NOVEL, bookDto.getGenre());
	}

	@Test
	public void updateBookTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/book/3").with(postProcessor)).andExpect(status().isOk()).andReturn();

		BookDto bookDto = objectMapper.readValue(result.getResponse().getContentAsString(), BookDto.class);
		assertNotNull(bookDto);
		bookDto.setTitle(TEST_UPDATE_TITLE);

		String bookJson = objectMapper.writeValueAsString(bookDto);
		result = mockMvc.perform(put("/book").content(bookJson).contentType(MediaType.APPLICATION_JSON).with(postProcessor))
				.andExpect(status().isOk()).andReturn();

		bookDto = objectMapper.readValue(result.getResponse().getContentAsString(), BookDto.class);

		assertNotNull(bookDto);
		assertTrue(bookDto.getId() > 0);
		assertEquals(TEST_UPDATE_TITLE, bookDto.getTitle());
		assertEquals(TEST_UPDATE_ISBN, bookDto.getIsbn());
		assertEquals(Genre.POEM, bookDto.getGenre());
	}
}
