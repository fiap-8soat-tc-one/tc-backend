package com.fiap.tc.core.application.usecase.product;

import com.fiap.tc.core.application.usecase.ports.in.product.UpdateProductInputPort;
import com.fiap.tc.core.application.usecase.ports.out.product.UpdateProductOutputPort;
import com.fiap.tc.core.domain.entities.Product;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductUseCase implements UpdateProductInputPort {
    private final UpdateProductOutputPort updateProductNameOutputPort;

    public UpdateProductUseCase(UpdateProductOutputPort updateProductNameOutputPort) {
        this.updateProductNameOutputPort = updateProductNameOutputPort;
    }

    @Override
    public Product update(Product product) {
        return updateProductNameOutputPort.update(product);
    }
}
