package com.ayadykin.ggstars.test.library.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ayadykin.ggstars.test.library.Application;
import com.ayadykin.ggstars.test.library.config.WebConfiguration;
import com.ayadykin.ggstars.test.library.dto.AuthorDto;
import com.ayadykin.ggstars.test.library.entity.enums.Sex;
import com.ayadykin.ggstars.test.library.exception.LibraryException;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { WebConfiguration.class, Application.class })
public class AuthorServiceTest {

	private final static String TEST_FIRST_NAME = "Николай";
	private final static String TEST_LAST_NAME = "Гоголь";
	private final static String UPDATED_NAME = "Микола";

	@Autowired
	private AuthorService authorService;

	@Test
	public void getAllAuthorsTest() {
		List<AuthorDto> authors = authorService.getAllAuthors();
		assertTrue(!authors.isEmpty());
	}

	@Test
	public void getAuthorTest() {
		AuthorDto author = authorService.getAuthor(1);
		assertNotNull(author);
	}

	@Test(expected = LibraryException.class)
	public void errorGetAuthorTest() {
		authorService.getAuthor(0);
	}

	@Test
	public void createAuthorTest() {
		AuthorDto author = new AuthorDto();
		author.setFirstName(TEST_FIRST_NAME);
		author.setLastName(TEST_LAST_NAME);
		author.setSex(Sex.MALE);
		author.setBirthDate(LocalDate.now());
		author = authorService.createAuthor(author);
		assertNotNull(author.getId());

		AuthorDto authorDto = authorService.getAuthor(author.getId());
		assertNotNull(authorDto);
	}

	@Test
	public void updateAuthorTest() {
		AuthorDto authorDto = new AuthorDto();
		authorDto.setFirstName(TEST_FIRST_NAME);
		authorDto.setLastName(TEST_LAST_NAME);
		authorDto.setSex(Sex.MALE);
		authorDto.setBirthDate(LocalDate.now());
		authorDto = authorService.createAuthor(authorDto);
		assertNotNull(authorDto.getId());

		authorDto.setFirstName(UPDATED_NAME);
		authorDto = authorService.updateAuthor(authorDto);

		assertEquals(UPDATED_NAME, authorDto.getFirstName());
	}
}
