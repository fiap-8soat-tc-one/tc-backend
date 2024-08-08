package com.fiap.tc.core.application.ports.in.product;

import com.fiap.tc.core.domain.entities.Product;
import com.fiap.tc.adapters.driver.presentation.requests.ProductRequest;

public interface RegisterProductInputPort {
    Product register(ProductRequest productRequest);
}
