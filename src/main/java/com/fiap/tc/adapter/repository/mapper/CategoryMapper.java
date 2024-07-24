package com.fiap.tc.adapter.repository.mapper;

import com.fiap.tc.adapter.repository.entity.CategoryEntity;
import com.fiap.tc.adapter.repository.mapper.base.MapperEntity;
import com.fiap.tc.core.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CategoryMapper extends MapperEntity<CategoryEntity, Category> {

    @Override
    @Mapping(target = "active", source = "audit.active")
    Category fromEntity(CategoryEntity categoryEntity);

    @Override
    @Mapping(source = "active", target = "audit.active")
    CategoryEntity toEntity(Category category);


}


