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
import com.ayadykin.ggstars.test.library.entity.enums.Genre;
import com.ayadykin.ggstars.test.library.exception.LibraryException;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { WebConfiguration.class, Application.class })
public class BookServiceTest {

	private final static String TEST_BOOK_ISBN = "1233";
	private final static String TEST_BOOK = "Ревизор";
	private final static String TEST_ISBN = "1234567";
	private final static String UPDATED_BOOK = "Женитьба";
	private final static String UPDATED_ISBN = "Женитьба";

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

	@Test(expected = LibraryException.class)
	public void ErrorGetBookTest() {
		bookService.getBook(0);
	}

	@Test
	public void getBookByIsbnTest() {
		BookDto book = bookService.getBookByIsbn(TEST_BOOK_ISBN);
		assertNotNull(book);
	}

	@Test(expected = LibraryException.class)
	public void errorGetBookByIsbnTest() {
		bookService.getBookByIsbn("0");
	}

	@Test
	public void createBookTest() {
		BookDto book = new BookDto();
		book.setTitle(TEST_BOOK);
		book.setIsbn(TEST_ISBN);
		book.setGenre(Genre.DRAMA);
		book = bookService.createBook(book);
		assertNotNull(book.getId());

		BookDto bookDto = bookService.getBook(book.getId());
		assertNotNull(bookDto);
	}

	@Test
	public void updateBookTest() {
		BookDto bookDto = new BookDto();
		bookDto.setTitle(TEST_BOOK);
		bookDto.setIsbn(TEST_ISBN);
		bookDto.setGenre(Genre.DRAMA);
		bookDto = bookService.createBook(bookDto);
		assertNotNull(bookDto.getId());

		bookDto.setTitle(UPDATED_BOOK);
		bookDto.setIsbn(UPDATED_ISBN);
		bookDto = bookService.updateBook(bookDto);

		assertEquals(UPDATED_BOOK, bookDto.getTitle());
		assertEquals(UPDATED_ISBN, bookDto.getIsbn());
	}

	@Test(expected = LibraryException.class)
	public void errorUpdateBookTest() {
		bookService.updateBook(new BookDto());
	}
}
