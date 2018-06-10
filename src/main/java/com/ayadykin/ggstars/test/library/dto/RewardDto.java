package com.ayadykin.ggstars.test.library.dto;

import com.ayadykin.ggstars.test.library.views.RewardView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RewardDto {
	
	@JsonView(RewardView.class)
	private int year;
	@JsonView(RewardView.class)
	private String title;
}
