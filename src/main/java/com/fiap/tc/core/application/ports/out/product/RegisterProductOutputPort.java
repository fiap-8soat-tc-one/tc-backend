package com.fiap.tc.core.application.ports.out.product;

import com.fiap.tc.core.domain.entities.Product;

public interface RegisterProductOutputPort {
    Product save(Product product);
}
