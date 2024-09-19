package com.fiap.tc.core.application.usecase.ports.out.product;

import com.fiap.tc.core.domain.entities.Product;

import java.util.UUID;

public interface LoadProductOutputPort {
    Product load(UUID idProduct);
}

