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
import com.ayadykin.ggstars.test.library.dto.BookDto;
import com.ayadykin.ggstars.test.library.entity.Genre;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { WebConfiguration.class, Application.class })
public class BookServiceTest {

	@Autowired
	private BookService bookService;
	
	@Test
    public void getAllBooksTest() {
		List<BookDto> books = bookService.getAllBooks();
        assertEquals(3, books.size());
    }
	
	@Test
    public void getBookTest() {
		BookDto book = bookService.getBook(1);
        assertNotNull(book);
    }
	
	@Test
    public void createBookTest() {
		BookDto book = new BookDto();
		book.setTitle("createBookTest");
		book.setIsbn("123");
		book.setGenre(Genre.DRAMA);
		book = bookService.createBook(book);
		assertNotNull(book.getId());
		
		BookDto bookDto = bookService.getBook(book.getId());
		assertNotNull(bookDto);
    }
	
	@Test
    public void updateBookTest() {
		BookDto bookDto = new BookDto();
		bookDto.setTitle("updateBookTest");
		bookDto.setIsbn("123");
		bookDto.setGenre(Genre.DRAMA);
		bookDto = bookService.createBook(bookDto);
		assertNotNull(bookDto.getId());
		
		bookDto.setTitle("Test updated");
		bookDto = bookService.updateBook(bookDto);
		
		assertEquals("Test updated", bookDto.getTitle());
    }
}
