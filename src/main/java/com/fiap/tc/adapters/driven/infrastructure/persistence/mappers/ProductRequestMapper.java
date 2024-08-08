package com.fiap.tc.adapters.driven.infrastructure.persistence.mappers;

import com.fiap.tc.adapters.driven.infrastructure.persistence.mappers.base.MapperEntity;
import com.fiap.tc.core.domain.model.Product;
import com.fiap.tc.adapters.driver.presentation.requests.ProductRequest;
import org.mapstruct.Mapper;

@Mapper
public interface ProductRequestMapper extends MapperEntity<Product, ProductRequest> {

    @Override
    ProductRequest fromEntity(Product product);

    @Override
    Product toEntity(ProductRequest productRequest);

}


