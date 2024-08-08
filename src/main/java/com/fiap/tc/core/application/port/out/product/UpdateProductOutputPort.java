package com.fiap.tc.core.port.out.product;

import com.fiap.tc.core.domain.model.Product;

public interface UpdateProductOutputPort {
    Product update(Product product);
}
