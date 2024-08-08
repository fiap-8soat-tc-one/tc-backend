package com.fiap.tc.adapter.repository.mapper;

import com.fiap.tc.adapter.repository.mapper.base.MapperEntity;
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


