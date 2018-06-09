package com.ayadykin.ggstars.test.library.service;

import java.util.List;

import com.ayadykin.ggstars.test.library.dto.BookDto;

public interface BookService {

	List<BookDto> getAllBooks();

	BookDto getBook(long bookId);

	BookDto getBookByIsbn(String isbn);

	BookDto createBook(BookDto bookDto);

	BookDto updateBook(BookDto bookDto);
}
