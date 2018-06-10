package com.ayadykin.ggstars.test.library.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.ayadykin.ggstars.test.library.entity.enums.Genre;
import com.ayadykin.ggstars.test.library.views.AuthorInfoView;
import com.ayadykin.ggstars.test.library.views.AuthorShortInfoView;
import com.ayadykin.ggstars.test.library.views.BookInfoView;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(exclude = {"authors"})
public class BookDto implements Serializable {

	@JsonView({BookInfoView.class, AuthorInfoView.class})
	private long id;

	@NotNull
	@JsonView({BookInfoView.class, AuthorInfoView.class, AuthorShortInfoView.class})
	private String title;
	@NotNull
	@JsonView({BookInfoView.class, AuthorInfoView.class})
	private String isbn;
	@NotNull
	@JsonView({BookInfoView.class, AuthorInfoView.class})
	private Genre genre;
	@JsonInclude(Include.NON_EMPTY)
	@JsonView(BookInfoView.class)
	private List<AuthorDto> authors;
}
