package com.fiap.tc.application.gateways;

import com.fiap.tc.domain.entities.Product;
import com.fiap.tc.domain.entities.ProductImage;

import java.util.List;
import java.util.UUID;

public interface ProductImagesGatewaySpec {

    Product register(UUID idProduct, List<ProductImage> images);

    void delete(UUID idProduct, List<UUID> productImagesWithIds);

}
