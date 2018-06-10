package com.ayadykin.ggstars.test.library.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayadykin.ggstars.test.library.dto.AuthorDto;
import com.ayadykin.ggstars.test.library.exception.LibraryException;
import com.ayadykin.ggstars.test.library.service.AuthorService;
import com.ayadykin.ggstars.test.library.utils.Utils;
import com.ayadykin.ggstars.test.library.views.AuthorInfoView;
import com.ayadykin.ggstars.test.library.views.AuthorShortInfoView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/author")
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@GetMapping
	@JsonView(AuthorInfoView.class)
	@ApiOperation(value = "View a list of available authors")
	public List<AuthorDto> getAllAuthors() {
		log.info(" -> getAllAuthors");
		return authorService.getAllAuthors();
	}

	@GetMapping("/{author_id}")
	@JsonView(AuthorInfoView.class)
	@ApiOperation(value = "Get author", response = AuthorDto.class)
	public AuthorDto getAuthor(@PathVariable("author_id") String id) {
		log.info(" -> getAuthor, id: {}", id);
		long authorId = Utils.parseId(id, LibraryException.ERROR_PARSE_AUTHOR_ID);
		return authorService.getAuthor(authorId);
	}

	@GetMapping("/info/short/{author_id}")
	@JsonView(AuthorShortInfoView.class)
	@ApiOperation(value = "Get author short info", response = AuthorDto.class)
	public AuthorDto getAuthorShortInfo(@PathVariable("author_id") String id) {
		log.info(" -> getAuthorShortInfo, id: {}", id);
		long authorId = Utils.parseId(id, LibraryException.ERROR_PARSE_AUTHOR_ID);
		return authorService.getAuthor(authorId);
	}

	@PostMapping
	@JsonView(AuthorInfoView.class)
	@ApiOperation(value = "Create a author", response = AuthorDto.class)
	public AuthorDto createAuthor(@RequestBody AuthorDto authorDto) {
		log.info(" -> createAuthor, authorDto: {}", authorDto);
		return authorService.createAuthor(authorDto);
	}

	@PutMapping
	@JsonView(AuthorInfoView.class)
	@ApiOperation(value = "Update a author", response = AuthorDto.class)
	public AuthorDto updateAuthor(@RequestBody AuthorDto authorDto) {
		log.info(" -> updateAuthor, authorDto: {}", authorDto);
		return authorService.updateAuthor(authorDto);
	}
}
