package com.ayadykin.ggstars.test.library.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.ayadykin.ggstars.test.library.entity.enums.Sex;
import com.ayadykin.ggstars.test.library.utils.Constants;
import com.ayadykin.ggstars.test.library.views.AuthorInfoView;
import com.ayadykin.ggstars.test.library.views.AuthorShortInfoView;
import com.ayadykin.ggstars.test.library.views.BookInfoView;
import com.ayadykin.ggstars.test.library.views.RewardView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(exclude = {"books"})
public class AuthorDto implements Serializable {
	
	@JsonView(AuthorInfoView.class)
	private long id;

	@NotEmpty
	@ApiModelProperty(required = true)
	@JsonProperty(value = "first_name")
	@JsonView({BookInfoView.class, AuthorInfoView.class, AuthorShortInfoView.class})
	private String firstName;
	@NotEmpty
	@ApiModelProperty(required = true)
	@JsonProperty(value = "last_name")
	@JsonView({BookInfoView.class, AuthorInfoView.class, AuthorShortInfoView.class})
	private String lastName;
	@Getter(AccessLevel.NONE)
	@JsonView(AuthorShortInfoView.class)
	private int age;
	@NotNull
	@ApiModelProperty(required = true)
	@JsonView({BookInfoView.class, AuthorInfoView.class})
	private Sex sex;
	@JsonInclude(Include.NON_EMPTY)
	@JsonView({AuthorInfoView.class, AuthorShortInfoView.class})
	private List<BookDto> books;
	
	@NotNull
	@ApiModelProperty(required = true)
	@JsonView({BookInfoView.class, AuthorInfoView.class})
	@JsonProperty(value = "birth_date")
	@JsonFormat(pattern = Constants.DATE_FORMAT)
	private LocalDate birthDate = LocalDate.now();

	@JsonInclude(Include.NON_EMPTY)
	@JsonView(RewardView.class)
	private List<RewardDto> rewards;

	public int getAge() {
		return LocalDate.now().minusYears(birthDate.getYear()).getYear();
	}
	
	
}
