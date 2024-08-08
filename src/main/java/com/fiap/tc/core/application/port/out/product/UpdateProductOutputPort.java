package com.fiap.tc.core.application.port.out.product;

import com.fiap.tc.core.domain.model.Product;

public interface UpdateProductOutputPort {
    Product update(Product product);
}
