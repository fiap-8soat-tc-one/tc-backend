package com.fiap.tc.application.usecases.product;

import com.fiap.tc.application.gateways.ProductImagesGatewaySpec;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeleteProductImagesUseCase {
    private final ProductImagesGatewaySpec productImagesGateway;

    public DeleteProductImagesUseCase(ProductImagesGatewaySpec productImagesGateway) {
        this.productImagesGateway = productImagesGateway;
    }

    public void delete(UUID idProduct, List<UUID> productImagesWithIds) {
        productImagesGateway.delete(idProduct, productImagesWithIds);
    }
}
