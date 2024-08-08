package com.fiap.tc.adapters.driver.presentation.mappers;

import com.fiap.tc.adapters.driver.presentation.mappers.base.MapperEntity;
import com.fiap.tc.adapters.driver.presentation.response.CategoryResponse;
import com.fiap.tc.core.domain.entities.Category;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper extends MapperEntity<Category, CategoryResponse> {

    @Override
    Category toDomain(CategoryResponse category);
    
    @Override
    CategoryResponse fromDomain(Category category);
}


