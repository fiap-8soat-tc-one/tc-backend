package com.fiap.tc.adapters.driver.presentation.mappers;

import com.fiap.tc.adapters.driver.presentation.mappers.base.MapperEntity;
import com.fiap.tc.adapters.driver.presentation.response.ProductResponse;
import com.fiap.tc.core.domain.entities.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductResponseMapper extends MapperEntity<Product, ProductResponse> {

    @Override
    Product toDomain(ProductResponse response);

    @Override
    ProductResponse fromDomain(Product entity);
}


