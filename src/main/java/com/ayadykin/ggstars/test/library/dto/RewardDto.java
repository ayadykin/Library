package com.ayadykin.ggstars.test.library.dto;

import com.ayadykin.ggstars.test.library.views.GeneralView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RewardDto {
	
	@JsonView(GeneralView.class)
	private int year;
	@JsonView(GeneralView.class)
	private String title;
}
