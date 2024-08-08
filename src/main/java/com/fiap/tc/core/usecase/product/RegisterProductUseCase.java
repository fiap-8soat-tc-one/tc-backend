package com.fiap.tc.core.usecase.product;

import com.fiap.tc.core.domain.model.Product;
import com.fiap.tc.core.domain.requests.ProductRequest;
import com.fiap.tc.core.port.in.product.RegisterProductInputPort;
import com.fiap.tc.core.port.out.product.RegisterProductOutputPort;
import org.springframework.stereotype.Service;

import static com.fiap.tc.adapter.repository.mapper.base.MapperConstants.PRODUCT_REQUEST_MAPPER;

@Service
public class RegisterProductUseCase implements RegisterProductInputPort {

    private final RegisterProductOutputPort registerProductOutputPort;

    public RegisterProductUseCase(RegisterProductOutputPort registerProductOutputPort) {
        this.registerProductOutputPort = registerProductOutputPort;
    }

    @Override
    public Product register(ProductRequest productRequest) {
        return registerProductOutputPort.saveOrUpdate(PRODUCT_REQUEST_MAPPER.toEntity(productRequest));
    }
}
