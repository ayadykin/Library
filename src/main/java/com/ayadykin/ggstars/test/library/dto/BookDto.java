package com.ayadykin.ggstars.test.library.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.ayadykin.ggstars.test.library.entity.Genre;
import com.ayadykin.ggstars.test.library.views.AuthorInfoView;
import com.ayadykin.ggstars.test.library.views.AuthorShortInfoView;
import com.ayadykin.ggstars.test.library.views.BookInfoView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookDto implements Serializable {

	@JsonView({BookInfoView.class, AuthorInfoView.class})
	private long id;

	@NotNull
	@JsonView({BookInfoView.class, AuthorShortInfoView.class})
	private String title;
	@NotNull
	@JsonView({BookInfoView.class, AuthorInfoView.class})
	private String isbn;
	@NotNull
	@JsonView({BookInfoView.class, AuthorInfoView.class})
	private Genre genre;
	
	@JsonView(BookInfoView.class)
	private List<AuthorDto> authors;
}
