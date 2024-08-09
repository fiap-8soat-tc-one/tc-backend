package com.fiap.tc.core.application.ports.in.product;

import com.fiap.tc.core.domain.entities.Product;

public interface UpdateProductInputPort {
    Product update(Product product);
}
