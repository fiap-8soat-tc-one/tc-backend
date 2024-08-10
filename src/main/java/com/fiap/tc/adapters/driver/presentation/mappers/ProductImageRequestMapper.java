package com.fiap.tc.adapters.driver.presentation.mappers;

import com.fiap.tc.adapters.driven.infrastructure.mappers.base.MapperEntity;
import com.fiap.tc.adapters.driver.presentation.requests.ProductImageRequest;
import com.fiap.tc.core.domain.entities.ProductImage;
import org.mapstruct.Mapper;

@Mapper
public interface ProductImageRequestMapper extends MapperEntity<ProductImage, ProductImageRequest> {

    @Override
    ProductImageRequest fromEntity(ProductImage productImage);

    @Override
    ProductImage toEntity(ProductImageRequest request);

}


