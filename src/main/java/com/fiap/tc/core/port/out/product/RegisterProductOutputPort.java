package com.fiap.tc.core.port.out.product;

import com.fiap.tc.core.domain.model.Product;

public interface RegisterProductOutputPort {
    Product saveOrUpdate(Product product);
}
