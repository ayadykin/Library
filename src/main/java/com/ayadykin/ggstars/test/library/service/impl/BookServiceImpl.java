package com.ayadykin.ggstars.test.library.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayadykin.ggstars.test.library.dto.BookDto;
import com.ayadykin.ggstars.test.library.entity.Book;
import com.ayadykin.ggstars.test.library.repository.BookRepository;
import com.ayadykin.ggstars.test.library.service.BookService;
import com.ayadykin.ggstars.test.library.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional(readOnly = true)
	public List<BookDto> getAllBooks() {
		log.debug(" -> getAllBooks");
		List<Book> books = (List<Book>) bookRepository.findAll();
		return books.stream().map((e) -> modelMapper.map(e, BookDto.class)).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public BookDto getBook(long bookId) {
		log.debug(" -> getBook, bookId: {}", bookId);
		Book book = bookRepository.findOne(bookId);
		return modelMapper.map(book, BookDto.class);
	}

	@Override
	@Transactional
	public BookDto createBook(BookDto bookDto) {
		log.debug(" -> createBook, bookDto: {}", bookDto);
		Book book = modelMapper.map(bookDto, Book.class);
		book = bookRepository.save(book);
		return modelMapper.map(book, BookDto.class);
	}

	@Override
	@Transactional
	public BookDto updateBook(BookDto bookDto) {
		log.debug(" -> updateBook, bookDto: {}", bookDto);
		Utils.validateId(bookDto.getId());
		Book book = modelMapper.map(bookDto, Book.class);
		book = bookRepository.save(book);
		return modelMapper.map(book, BookDto.class);
	}
}
