package com.fiap.tc.core.application.port.out.product;

import com.fiap.tc.core.domain.model.Product;
import com.fiap.tc.core.domain.model.ProductImage;

import java.util.List;
import java.util.UUID;

public interface RegisterProductImagesOutputPort {
    Product save(UUID idProduct, List<ProductImage> images);
}
