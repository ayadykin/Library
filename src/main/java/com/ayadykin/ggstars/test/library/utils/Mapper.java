package com.ayadykin.ggstars.test.library.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper<T, V> {

	private ModelMapper modelMapper = new ModelMapper();

	public T entityToDto(Object entity, Class<T> dto) {
		return modelMapper.map(entity, dto);
	}
	
	public V dtoToEntity(Object dto, Class<V> entity) {
		return modelMapper.map(dto, entity);
	}

	public List<T> map(List<V> entity, Class<T> dto) {
		return entity.stream().map((e) -> modelMapper.map(e, dto)).collect(Collectors.toList());
	}
}
