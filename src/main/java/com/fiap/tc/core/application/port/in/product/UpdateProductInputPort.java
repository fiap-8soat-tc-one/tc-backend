package com.fiap.tc.core.application.port.in.product;

import com.fiap.tc.core.domain.model.Product;
import com.fiap.tc.adapters.driver.presentation.requests.ProductRequest;

import java.util.UUID;

public interface UpdateProductInputPort {
    Product update(UUID idProduct, ProductRequest productRequest);
}
