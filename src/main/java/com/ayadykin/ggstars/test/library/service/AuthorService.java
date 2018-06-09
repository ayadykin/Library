package com.ayadykin.ggstars.test.library.service;

import java.util.List;

import com.ayadykin.ggstars.test.library.dto.AuthorDto;

public interface AuthorService {

	List<AuthorDto> getAllAuthors();

	AuthorDto getAuthor(long authorId);

	AuthorDto createAuthor(AuthorDto authorDto);

	AuthorDto updateAuthor(AuthorDto authorDto);

}
