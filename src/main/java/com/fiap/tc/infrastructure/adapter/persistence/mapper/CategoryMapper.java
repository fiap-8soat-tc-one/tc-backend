package com.fiap.tc.infrastructure.adapter.persistence.mapper;

import com.fiap.tc.core.domain.model.Category;
import com.fiap.tc.infrastructure.adapter.persistence.entity.CategoryEntity;
import com.fiap.tc.infrastructure.adapter.persistence.mapper.base.MapperEntity;
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


