package com.ayadykin.ggstars.test.library.api;

import static com.ayadykin.ggstars.test.library.exception.LibraryException.ERROR_PARSE_BOOK_ID;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayadykin.ggstars.test.library.dto.BookDto;
import com.ayadykin.ggstars.test.library.service.BookService;
import com.ayadykin.ggstars.test.library.utils.Utils;
import com.ayadykin.ggstars.test.library.views.BookInfoView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping
	@JsonView(BookInfoView.class)
	public List<BookDto> getAllBooks() {
		log.info(" -> getAllBooks");
		return bookService.getAllBooks();
	}

	@GetMapping("/{book_id}")
	@JsonView(BookInfoView.class)
	public BookDto getBook(@PathVariable("book_id") String id) {
		log.info(" -> getBook, id: {}", id);
		long bookId = Utils.parseId(id, ERROR_PARSE_BOOK_ID);
		BookDto bookDto = bookService.getBook(bookId);
		log.info(" <- getBook, bookDto: {}", bookDto);
		return bookDto;
	}

	@PostMapping
	@JsonView(BookInfoView.class)
	public BookDto createBook(@RequestBody BookDto bookDto) {
		log.info(" -> createBook, bookDto: {}", bookDto);
		return bookService.createBook(bookDto);
	}

	@PutMapping
	@JsonView(BookInfoView.class)
	public BookDto updateBook(@RequestBody BookDto bookDto) {
		log.info(" -> updateBook, bookDto: {}", bookDto);
		return bookService.updateBook(bookDto);
	}
}
