package com.fiap.tc.adapters.driver.presentation.mappers;

import com.fiap.tc.adapters.driver.presentation.mappers.base.MapperEntity;
import com.fiap.tc.adapters.driver.presentation.requests.ProductRequest;
import com.fiap.tc.core.domain.entities.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductRequestMapper extends MapperEntity<Product, ProductRequest> {

    @Override
    ProductRequest fromDomain(Product product);

    @Override
    Product toDomain(ProductRequest productRequest);
}


