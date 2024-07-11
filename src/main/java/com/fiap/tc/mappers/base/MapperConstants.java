package com.fiap.tc.mappers.base;

import com.fiap.tc.mappers.CategoryMapper;
import org.mapstruct.factory.Mappers;

public class MapperConstants {
	 private MapperConstants(){}
	 public static final CategoryMapper CATEGORY_MAPPER = Mappers.getMapper(CategoryMapper.class);

}
