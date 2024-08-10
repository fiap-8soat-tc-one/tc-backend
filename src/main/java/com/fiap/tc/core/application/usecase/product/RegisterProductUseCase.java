package com.fiap.tc.core.application.usecase.product;

import com.fiap.tc.core.application.ports.in.product.RegisterProductInputPort;
import com.fiap.tc.core.application.ports.out.product.RegisterProductOutputPort;
import com.fiap.tc.core.domain.entities.Product;
import org.springframework.stereotype.Service;

@Service
public class RegisterProductUseCase implements RegisterProductInputPort {

    private final RegisterProductOutputPort registerProductOutputPort;

    public RegisterProductUseCase(RegisterProductOutputPort registerProductOutputPort) {
        this.registerProductOutputPort = registerProductOutputPort;
    }

    @Override
    public Product register(Product product) {
        return registerProductOutputPort.saveOrUpdate(product);
    }
}
