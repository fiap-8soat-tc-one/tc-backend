package com.fiap.tc.core.application.usecase.product;

import com.fiap.tc.adapters.driver.presentation.mappers.base.MapperConstants;
import com.fiap.tc.core.application.ports.in.product.UpdateProductInputPort;
import com.fiap.tc.core.domain.entities.Product;
import com.fiap.tc.adapters.driver.presentation.requests.ProductRequest;
import com.fiap.tc.core.application.ports.out.product.UpdateProductOutputPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateProductUseCase implements UpdateProductInputPort {
    private final UpdateProductOutputPort updateProductNameOutputPort;

    public UpdateProductUseCase(UpdateProductOutputPort updateProductNameOutputPort) {
        this.updateProductNameOutputPort = updateProductNameOutputPort;
    }

    @Override
    public Product update(UUID idProduct, ProductRequest request) {
        var product = MapperConstants.PRODUCT_REQUEST_MAPPER.toDomain(request);
        product.setId(idProduct);
        return updateProductNameOutputPort.update(product);
    }
}
