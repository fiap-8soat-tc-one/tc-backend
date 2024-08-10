package com.fiap.tc.adapters.driven.infrastructure.mappers;

import com.fiap.tc.adapters.driven.infrastructure.mappers.base.MapperEntity;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.ProductImageEntity;
import com.fiap.tc.core.domain.entities.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductImageMapper extends MapperEntity<ProductImageEntity, ProductImage> {

    @Override
    @Mapping(target = "id", source = "uuid")
    ProductImage fromEntity(ProductImageEntity productImageEntity);

    @Override
    @Mapping(target = "id", ignore = true)
    ProductImageEntity toEntity(ProductImage productImage);

}


