package com.fiap.tc.core.application.usecase.product;

import com.fiap.tc.adapters.driven.infrastructure.persistence.mappers.base.MapperConstants;
import com.fiap.tc.core.application.port.in.product.UpdateProductInputPort;
import com.fiap.tc.core.domain.model.Product;
import com.fiap.tc.adapters.driver.presentation.requests.ProductRequest;
import com.fiap.tc.core.application.port.out.product.UpdateProductOutputPort;
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
        var product = MapperConstants.PRODUCT_REQUEST_MAPPER.toEntity(request);
        product.setId(idProduct);
        return updateProductNameOutputPort.update(product);
    }
}
