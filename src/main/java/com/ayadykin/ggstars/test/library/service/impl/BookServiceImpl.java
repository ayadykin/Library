package com.ayadykin.ggstars.test.library.service.impl;

import static com.ayadykin.ggstars.test.library.exception.LibraryException.EMPTY_BOOK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayadykin.ggstars.test.library.dto.BookDto;
import com.ayadykin.ggstars.test.library.entity.Book;
import com.ayadykin.ggstars.test.library.exception.LibraryException;
import com.ayadykin.ggstars.test.library.repository.BookRepository;
import com.ayadykin.ggstars.test.library.service.BookService;
import com.ayadykin.ggstars.test.library.utils.Mapper;
import com.ayadykin.ggstars.test.library.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private Mapper<BookDto, Book> mapper;

	@Override
	@Transactional(readOnly = true)
	public List<BookDto> getAllBooks() {
		log.debug(" -> getAllBooks");
		List<Book> books = (List<Book>) bookRepository.findAll();
		return mapper.map(books, BookDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public BookDto getBook(long bookId) {
		log.debug(" -> getBook, bookId: {}", bookId);
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new LibraryException(EMPTY_BOOK));
		return mapper.entityToDto(book, BookDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public BookDto getBookByIsbn(String isbn) {
		log.debug(" -> getBookByIsbn, isbn: {}", isbn);
		Book book = bookRepository.findByIsbn(isbn).orElseThrow(() -> new LibraryException(EMPTY_BOOK));
		return mapper.entityToDto(book, BookDto.class);
	}

	@Override
	@Transactional
	public BookDto createBook(BookDto bookDto) {
		log.debug(" -> createBook, bookDto: {}", bookDto);
		Book book = mapper.dtoToEntity(bookDto, Book.class);
		book = bookRepository.save(book);
		return mapper.entityToDto(book, BookDto.class);
	}

	@Override
	@Transactional
	public BookDto updateBook(BookDto bookDto) {
		log.debug(" -> updateBook, bookDto: {}", bookDto);
		Utils.validateId(bookDto.getId());
		Book book = mapper.dtoToEntity(bookDto, Book.class);
		book = bookRepository.save(book);
		return mapper.entityToDto(book, BookDto.class);
	}
}
