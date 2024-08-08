package com.fiap.tc.core.usecase.product;

import com.fiap.tc.adapters.repository.mapper.base.MapperConstants;
import com.fiap.tc.core.domain.model.Product;
import com.fiap.tc.core.domain.requests.ProductRequest;
import com.fiap.tc.core.port.in.product.UpdateProductInputPort;
import com.fiap.tc.core.port.out.product.UpdateProductOutputPort;
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
