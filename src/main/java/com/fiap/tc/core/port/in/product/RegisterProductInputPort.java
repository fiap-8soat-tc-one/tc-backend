package com.fiap.tc.core.port.in.product;

import com.fiap.tc.core.domain.model.Product;
import com.fiap.tc.core.domain.requests.ProductRequest;

public interface RegisterProductInputPort {
    Product register(ProductRequest productRequest);
}
