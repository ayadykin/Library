package com.ayadykin.ggstars.test.library.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.ayadykin.ggstars.test.library.entity.Sex;
import com.ayadykin.ggstars.test.library.utils.Constants;
import com.ayadykin.ggstars.test.library.views.AuthorInfoView;
import com.ayadykin.ggstars.test.library.views.AuthorShortInfoView;
import com.ayadykin.ggstars.test.library.views.BookInfoView;
import com.ayadykin.ggstars.test.library.views.GeneralView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthorDto implements Serializable {
	
	@JsonView(AuthorInfoView.class)
	private long id;

	@NotNull
	@JsonProperty(value = "first_name")
	@JsonView({BookInfoView.class, AuthorShortInfoView.class})
	private String firstName;
	@NotNull
	@JsonProperty(value = "last_name")
	@JsonView({BookInfoView.class, AuthorShortInfoView.class})
	private String lastName;
	@Getter(AccessLevel.NONE)
	@JsonView(AuthorShortInfoView.class)
	private int age;
	@NotNull
	@JsonView({BookInfoView.class, AuthorInfoView.class})
	private Sex sex;
	@JsonView(AuthorShortInfoView.class)
	private List<BookDto> books;
	
	@NotNull
	@JsonView(BookInfoView.class)
	@JsonProperty(value = "birth_date")
	@JsonFormat(pattern = Constants.DATE_FORMAT)
	private LocalDate birthDate;

	@JsonView(GeneralView.class)
	private List<RewardDto> rewards;

	public int getAge() {
		return LocalDate.now().minusYears(birthDate.getYear()).getYear();
	}
	
	
}
