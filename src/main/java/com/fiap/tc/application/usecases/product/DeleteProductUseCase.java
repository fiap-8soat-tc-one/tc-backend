package com.fiap.tc.application.usecases.product;

import com.fiap.tc.application.gateways.ProductGatewaySpec;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteProductUseCase {

    private final ProductGatewaySpec productGateway;

    public DeleteProductUseCase(ProductGatewaySpec productGateway) {
        this.productGateway = productGateway;
    }

    public void delete(UUID idProduct) {
        productGateway.delete(idProduct);
    }
}
