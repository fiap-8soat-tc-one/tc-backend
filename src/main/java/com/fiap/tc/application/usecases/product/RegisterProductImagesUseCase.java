package com.fiap.tc.application.usecases.product;

import com.fiap.tc.application.gateways.ProductImagesGatewaySpec;
import com.fiap.tc.domain.entities.Product;
import com.fiap.tc.domain.entities.ProductImage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RegisterProductImagesUseCase {
    private final ProductImagesGatewaySpec productImagesGateway;

    public RegisterProductImagesUseCase(ProductImagesGatewaySpec productImagesGateway) {
        this.productImagesGateway = productImagesGateway;
    }

    public Product register(UUID idProduct, List<ProductImage> images) {

        return productImagesGateway.register(idProduct, images);
    }
}
