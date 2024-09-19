package com.fiap.tc.core.application.usecase.ports.in.product;

import com.fiap.tc.core.domain.entities.Product;

public interface RegisterProductInputPort {
    Product register(Product product);
}
