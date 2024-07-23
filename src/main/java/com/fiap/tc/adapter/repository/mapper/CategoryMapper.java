package com.fiap.tc.adapter.repository.mapper;

import com.fiap.tc.adapter.repository.entity.CustomerEntity;
import com.fiap.tc.core.domain.model.Category;
import com.fiap.tc.adapter.repository.entity.CategoryEntity;
import com.fiap.tc.adapter.repository.mapper.base.MapperEntity;
import com.fiap.tc.core.domain.model.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper extends MapperEntity<CategoryEntity, Category> {
	
	@Override
	Category fromEntity(CategoryEntity categoryEntity);

	@Override
	CategoryEntity toEntity(Category category);


}


