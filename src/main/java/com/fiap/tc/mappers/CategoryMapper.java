package com.fiap.tc.mappers;

import com.fiap.tc.domains.products.Category;
import com.fiap.tc.dto.CategoryDto;
import com.fiap.tc.mappers.base.MapperEntity;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper extends MapperEntity<Category, CategoryDto> {
	
	@Override
	CategoryDto fromEntity(Category category);

	@Override
	Category toEntity(CategoryDto categoryDto);

}
