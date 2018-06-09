package com.ayadykin.ggstars.test.library.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import com.ayadykin.ggstars.test.library.entity.Sex;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { WebConfiguration.class, Application.class })
public class AuthorServiceTest {

	@Autowired
	private AuthorService authorService;

	@Test
	public void getAllAuthorsTest() {
		List<AuthorDto> authors = authorService.getAllAuthors();
		assertEquals(5, authors.size());
	}
	
	@Test
    public void getAuthorTest() {
		AuthorDto author = authorService.getAuthor(1);
        assertNotNull(author);
    }
	
	@Test
    public void createAuthorTest() {
		AuthorDto author = new AuthorDto();
		author.setFirstName("Test firstname");
		author.setLastName("Test lastname");
		author.setSex(Sex.MALE);
		author = authorService.createAuthor(author);
		assertNotNull(author.getId());
		
		AuthorDto authorDto = authorService.getAuthor(author.getId());
		assertNotNull(authorDto);
    }
	
	@Test
    public void updateAuthorTest() {
		AuthorDto authorDto = new AuthorDto();
		authorDto.setFirstName("Test firstname");
		authorDto.setLastName("Test lastname");
		authorDto.setSex(Sex.MALE);
		authorDto = authorService.createAuthor(authorDto);
		assertNotNull(authorDto.getId());
		
		authorDto.setFirstName("Updated firstname");
		authorDto = authorService.updateAuthor(authorDto);
		
		assertEquals("Updated firstname", authorDto.getFirstName());
    }
}
