package com.fiap.tc.core.application.usecase.ports.in.product;

import com.fiap.tc.core.domain.entities.Product;
import com.fiap.tc.core.domain.entities.ProductImage;

import java.util.List;
import java.util.UUID;

public interface RegisterProductImagesInputPort {
    Product register(UUID idProduct, List<ProductImage> images);
}
