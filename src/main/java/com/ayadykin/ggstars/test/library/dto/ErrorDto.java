package com.ayadykin.ggstars.test.library.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto implements Serializable {

	private String message;
	@JsonInclude(value = Include.NON_EMPTY)
	private List<String> wrong_fields = new ArrayList<>();

	public ErrorDto(String message) {
		this.message = message;
	}

	public ErrorDto addError(String error) {
		wrong_fields.add(error);
		return this;
	}
}
