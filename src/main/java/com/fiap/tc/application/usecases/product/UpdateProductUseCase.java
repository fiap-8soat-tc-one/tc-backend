package com.fiap.tc.application.usecases.product;

import com.fiap.tc.application.gateways.ProductGatewaySpec;
import com.fiap.tc.domain.entities.Product;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductUseCase {
    private final ProductGatewaySpec productGateway;

    public UpdateProductUseCase(ProductGatewaySpec productGateway) {
        this.productGateway = productGateway;
    }

    public Product update(Product product) {
        return productGateway.update(product);
    }
}
