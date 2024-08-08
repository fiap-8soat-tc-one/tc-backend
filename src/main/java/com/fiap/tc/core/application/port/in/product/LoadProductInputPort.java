package com.fiap.tc.core.application.port.in.product;

import com.fiap.tc.core.domain.model.Product;

import java.util.UUID;

public interface LoadProductInputPort {
    Product load(UUID idProduct);
}
