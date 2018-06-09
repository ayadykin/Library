package com.ayadykin.ggstars.test.library.utils;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper<T> {

	private ModelMapper modelMapper = new ModelMapper();

	public T map(Object entity, Class<T> dto) {
		return modelMapper.map(entity, dto);
	}

	public List<T> map(List<? extends Serializable> entity, Class<T> dto) {
		return entity.stream().map((e) -> modelMapper.map(e, dto)).collect(Collectors.toList());
	}
}
