package com.fiap.tc.infrastructure.presentation.mappers;

import com.fiap.tc.infrastructure.presentation.mappers.base.MapperEntity;
import com.fiap.tc.infrastructure.presentation.response.ProductImageResponse;
import com.fiap.tc.domain.entities.ProductImage;
import org.mapstruct.Mapper;

@Mapper
public interface ProductImageResponseMapper extends MapperEntity<ProductImage, ProductImageResponse> {

    @Override
    ProductImage toDomain(ProductImageResponse response);

    @Override
    ProductImageResponse fromDomain(ProductImage productImage);
}


