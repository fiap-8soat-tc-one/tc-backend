package com.fiap.tc.adapters.driven.infrastructure.persistence.mappers;

import com.fiap.tc.adapters.driven.infrastructure.persistence.entity.CategoryEntity;
import com.fiap.tc.adapters.driven.infrastructure.persistence.mappers.base.MapperEntity;
import com.fiap.tc.core.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CategoryMapper extends MapperEntity<CategoryEntity, Category> {

    @Override
    @Mapping(target = "active", source = "audit.active")
    @Mapping(target = "id", source = "uuid")
    Category fromEntity(CategoryEntity categoryEntity);

    @Override
    @Mapping(source = "active", target = "audit.active")
    @Mapping(target = "id", ignore = true)
    CategoryEntity toEntity(Category category);


}


