package com.fiap.tc.adapters.repository.mapper;

import com.fiap.tc.adapters.repository.mapper.base.MapperEntity;
import com.fiap.tc.core.domain.model.Product;
import com.fiap.tc.core.domain.requests.ProductRequest;
import org.mapstruct.Mapper;

@Mapper
public interface ProductRequestMapper extends MapperEntity<Product, ProductRequest> {

    @Override
    ProductRequest fromEntity(Product product);

    @Override
    Product toEntity(ProductRequest productRequest);

}


