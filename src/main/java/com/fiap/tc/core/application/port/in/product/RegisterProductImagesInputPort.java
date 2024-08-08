package com.fiap.tc.core.application.port.in.product;

import com.fiap.tc.core.domain.model.Product;
import com.fiap.tc.core.domain.requests.RegisterProductImagesRequest;

public interface RegisterProductImagesInputPort {
    Product register(RegisterProductImagesRequest request);
}
