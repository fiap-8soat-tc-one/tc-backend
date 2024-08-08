package com.fiap.tc.core.application.ports.in.product;

import com.fiap.tc.core.domain.entities.Product;
import com.fiap.tc.adapters.driver.presentation.requests.ProductRequest;

import java.util.UUID;

public interface UpdateProductInputPort {
    Product update(UUID idProduct, ProductRequest productRequest);
}
