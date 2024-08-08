package com.fiap.tc.adapters.driver.presentation.mappers;

import com.fiap.tc.adapters.driver.presentation.mappers.base.MapperEntity;
import com.fiap.tc.adapters.driver.presentation.response.ProductImageResponse;
import com.fiap.tc.core.domain.entities.ProductImage;
import org.mapstruct.Mapper;

@Mapper
public interface ProductImageResponseMapper extends MapperEntity<ProductImage, ProductImageResponse> {

    @Override
    ProductImage toDomain(ProductImageResponse response);

    @Override
    ProductImageResponse fromDomain(ProductImage entity);
}


