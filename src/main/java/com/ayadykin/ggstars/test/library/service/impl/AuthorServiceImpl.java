package com.ayadykin.ggstars.test.library.service.impl;

import static com.ayadykin.ggstars.test.library.exception.LibraryException.EMPTY_AUTHOR;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayadykin.ggstars.test.library.dto.AuthorDto;
import com.ayadykin.ggstars.test.library.entity.Author;
import com.ayadykin.ggstars.test.library.exception.LibraryException;
import com.ayadykin.ggstars.test.library.repository.AuthorRepository;
import com.ayadykin.ggstars.test.library.service.AuthorService;
import com.ayadykin.ggstars.test.library.utils.Mapper;
import com.ayadykin.ggstars.test.library.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private Mapper<AuthorDto, Author> mapper;

	@Override
	@Transactional(readOnly = true)
	public List<AuthorDto> getAllAuthors() {
		log.debug(" -> getAllAuthors");
		List<Author> books = (List<Author>) authorRepository.findAll();
		return mapper.map(books, AuthorDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public AuthorDto getAuthor(long authorId) {
		log.debug(" -> getAuthor, authorId: {}", authorId);
		Author author = authorRepository.findById(authorId).orElseThrow(() -> new LibraryException(EMPTY_AUTHOR));
		return mapper.entityToDto(author, AuthorDto.class);
	}

	@Override
	@Transactional
	public AuthorDto createAuthor(AuthorDto authorDto) {
		log.debug(" -> createAuthor, authorDto: {}", authorDto);
		Utils.validateIdGt(authorDto.getId());
		Author author = mapper.dtoToEntity(authorDto, Author.class);
		author = authorRepository.save(author);
		return mapper.entityToDto(author, AuthorDto.class);
	}

	@Override
	@Transactional
	public AuthorDto updateAuthor(AuthorDto authorDto) {
		log.debug(" -> updateAuthor, authorDto: {}", authorDto);
		Utils.validateIdLess(authorDto.getId());
		Author author = mapper.dtoToEntity(authorDto, Author.class);
		author = authorRepository.save(author);
		return mapper.entityToDto(author, AuthorDto.class);
	}

}
