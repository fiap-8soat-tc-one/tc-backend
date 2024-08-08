package com.fiap.tc.core.application.ports.in.product;

import com.fiap.tc.core.domain.entities.Product;

import java.util.UUID;

public interface LoadProductInputPort {
    Product load(UUID idProduct);
}
