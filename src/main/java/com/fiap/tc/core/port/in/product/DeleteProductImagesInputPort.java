package com.fiap.tc.core.port.in.product;

import com.fiap.tc.core.domain.model.Product;
import com.fiap.tc.core.domain.requests.DeleteProductImagesRequest;

import java.util.UUID;

public interface DeleteProductImagesInputPort {
    Product register(UUID idProduct, DeleteProductImagesRequest request);
}
