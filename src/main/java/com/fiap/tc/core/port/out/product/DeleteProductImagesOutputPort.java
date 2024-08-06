package com.fiap.tc.core.port.out.product;

import com.fiap.tc.core.domain.model.Product;

import java.util.List;
import java.util.UUID;

public interface DeleteProductImagesOutputPort {
    Product delete(UUID idProduct, List<UUID> productImagesWithIds);
}
