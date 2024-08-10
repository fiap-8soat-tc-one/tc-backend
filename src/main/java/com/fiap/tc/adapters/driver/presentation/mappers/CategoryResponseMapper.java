package com.fiap.tc.adapters.driver.presentation.mappers;

import com.fiap.tc.adapters.driver.presentation.mappers.base.MapperEntity;
import com.fiap.tc.adapters.driver.presentation.response.CategoryResponse;
import com.fiap.tc.core.domain.entities.Category;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryResponseMapper extends MapperEntity<Category, CategoryResponse> {

    @Override
    Category toDomain(CategoryResponse response);

    @Override
    CategoryResponse fromDomain(Category category);
}


