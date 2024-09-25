package com.fiap.tc.application.usecases.product;

import com.fiap.tc.application.gateways.ProductGatewaySpec;
import com.fiap.tc.domain.entities.Product;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoadProductUseCase {
    private final ProductGatewaySpec productGateway;

    public LoadProductUseCase(ProductGatewaySpec productGateway) {
        this.productGateway = productGateway;
    }

    public Product load(UUID idProduct) {
        return productGateway.load(idProduct);
    }
}
