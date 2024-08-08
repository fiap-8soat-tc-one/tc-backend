package com.fiap.tc.core.usecase.product;

import com.fiap.tc.core.domain.model.Product;
import com.fiap.tc.core.port.in.product.LoadProductInputPort;
import com.fiap.tc.core.port.out.product.LoadProductOutputPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoadProductUseCase implements LoadProductInputPort {
    private final LoadProductOutputPort loadProductOutputPort;

    public LoadProductUseCase(LoadProductOutputPort loadProductOutputPort) {
        this.loadProductOutputPort = loadProductOutputPort;
    }

    @Override
    public Product load(UUID idProduct) {
        return loadProductOutputPort.load(idProduct);
    }
}
