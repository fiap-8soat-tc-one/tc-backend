package com.fiap.tc.infrastructure.presentation.mappers;

import com.fiap.tc.infrastructure.presentation.mappers.base.MapperEntity;
import com.fiap.tc.infrastructure.presentation.response.CategoryResponse;
import com.fiap.tc.domain.entities.Category;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryResponseMapper extends MapperEntity<Category, CategoryResponse> {

    @Override
    Category toDomain(CategoryResponse response);

    @Override
    CategoryResponse fromDomain(Category category);
}


