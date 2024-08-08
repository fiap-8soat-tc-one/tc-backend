package com.fiap.tc.adapters.repository.mapper;

import com.fiap.tc.adapters.repository.mapper.base.MapperEntity;
import com.fiap.tc.core.domain.model.ProductImage;
import com.fiap.tc.core.domain.requests.ProductImageRequest;
import org.mapstruct.Mapper;

@Mapper
public interface ProductImageRequestMapper extends MapperEntity<ProductImage, ProductImageRequest> {

    @Override
    ProductImageRequest fromEntity(ProductImage productImage);

    @Override
    ProductImage toEntity(ProductImageRequest productImageRequest);

}


