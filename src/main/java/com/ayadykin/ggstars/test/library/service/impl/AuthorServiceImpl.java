package com.ayadykin.ggstars.test.library.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayadykin.ggstars.test.library.dto.AuthorDto;
import com.ayadykin.ggstars.test.library.dto.BookDto;
import com.ayadykin.ggstars.test.library.entity.Author;
import com.ayadykin.ggstars.test.library.repository.AuthorRepository;
import com.ayadykin.ggstars.test.library.service.AuthorService;
import com.ayadykin.ggstars.test.library.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional(readOnly = true)
	public List<AuthorDto> getAllAuthors() {
		log.debug(" -> getAllAuthors");
		List<Author> books = (List<Author>) authorRepository.findAll();
		return books.stream().map((e) -> modelMapper.map(e, AuthorDto.class)).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public AuthorDto getAuthor(long authorId) {
		log.debug(" -> getAuthor, authorId: {}", authorId);
		Author author = authorRepository.findOne(authorId);
		return modelMapper.map(author, AuthorDto.class);
	}

	@Override
	@Transactional
	public AuthorDto createAuthor(AuthorDto authorDto) {
		log.debug(" -> createAuthor, authorDto: {}", authorDto);
		Author author = modelMapper.map(authorDto, Author.class);
		author = authorRepository.save(author);
		return modelMapper.map(author, AuthorDto.class);
	}

	@Override
	@Transactional
	public AuthorDto updateAuthor(AuthorDto authorDto) {
		log.debug(" -> updateAuthor, authorDto: {}", authorDto);
		Utils.validateId(authorDto.getId());
		Author author = modelMapper.map(authorDto, Author.class);
		author = authorRepository.save(author);
		return modelMapper.map(author, AuthorDto.class);
	}

}
