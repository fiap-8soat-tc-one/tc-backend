package com.fiap.tc.adapter.repository.mapper.base;

import com.fiap.tc.adapter.repository.mapper.CategoryMapper;
import org.mapstruct.factory.Mappers;

public class MapperConstants {
	 private MapperConstants(){}
	 public static final CategoryMapper CATEGORY_MAPPER = Mappers.getMapper(CategoryMapper.class);

}
