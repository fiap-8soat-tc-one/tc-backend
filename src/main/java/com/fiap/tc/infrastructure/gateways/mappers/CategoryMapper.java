package com.fiap.tc.infrastructure.gateways.mappers;

import com.fiap.tc.infrastructure.persistence.entities.CategoryEntity;
import com.fiap.tc.infrastructure.gateways.mappers.base.MapperEntity;
import com.fiap.tc.domain.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CategoryMapper extends MapperEntity<CategoryEntity, Category> {

    @Override
    @Mapping(target = "id", source = "uuid")
    Category fromEntity(CategoryEntity categoryEntity);

    @Override
    @Mapping(target = "id", ignore = true)
    CategoryEntity toEntity(Category category);


}


