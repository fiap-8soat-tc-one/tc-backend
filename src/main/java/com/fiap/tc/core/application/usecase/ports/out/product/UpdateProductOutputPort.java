package com.fiap.tc.core.application.usecase.ports.out.product;

import com.fiap.tc.core.domain.entities.Product;

public interface UpdateProductOutputPort {
    Product update(Product product);
}
