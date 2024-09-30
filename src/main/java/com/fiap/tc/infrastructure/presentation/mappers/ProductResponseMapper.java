package com.fiap.tc.infrastructure.presentation.mappers;

import com.fiap.tc.infrastructure.presentation.mappers.base.MapperEntity;
import com.fiap.tc.infrastructure.presentation.response.ProductResponse;
import com.fiap.tc.domain.entities.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductResponseMapper extends MapperEntity<Product, ProductResponse> {

    @Override
    Product toDomain(ProductResponse response);

    @Override
    ProductResponse fromDomain(Product product);
}


