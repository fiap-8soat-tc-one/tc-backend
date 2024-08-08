package com.fiap.tc.core.application.port.out.product;

import com.fiap.tc.core.domain.model.Product;

import java.util.UUID;

public interface LoadProductOutputPort {
    Product load(UUID idProduct);
}

