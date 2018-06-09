package com.ayadykin.ggstars.test.library.api;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;

import com.ayadykin.ggstars.test.library.Application;
import com.ayadykin.ggstars.test.library.config.WebConfiguration;
import com.ayadykin.ggstars.test.library.dto.BookDto;
import com.ayadykin.ggstars.test.library.dto.ErrorDto;
import com.ayadykin.ggstars.test.library.exception.LibraryException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { WebConfiguration.class, Application.class })
public class BookControllerTest extends GeneralControllerTest {

	@Test
	public void getBookTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/book/1").with(postProcessor)).andExpect(status().isOk()).andReturn();
		
		BookDto bookDto = objectMapper.readValue(result.getResponse().getContentAsString(), BookDto.class);
		assertEquals("1233", bookDto.getIsbn());
	}

	@Test
	public void errorGetBookTest() throws Exception {

		MvcResult result = mockMvc.perform(get("/book/10").with(postProcessor)).andExpect(status().is4xxClientError()).andReturn();

		ErrorDto expectedErrorDto = new ErrorDto(LibraryException.EMPTY_BOOK);
		assertEquals(objectMapper.writeValueAsString(expectedErrorDto), result.getResponse().getContentAsString());
	}
}
