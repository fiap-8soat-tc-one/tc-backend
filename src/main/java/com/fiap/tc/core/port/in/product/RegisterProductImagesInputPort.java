package com.fiap.tc.core.port.in.product;

import com.fiap.tc.core.domain.model.Product;
import com.fiap.tc.core.domain.requests.RegisterProductImagesRequest;

import java.util.UUID;

public interface RegisterProductImagesInputPort {
    Product register(UUID idProduct, RegisterProductImagesRequest request);
}
