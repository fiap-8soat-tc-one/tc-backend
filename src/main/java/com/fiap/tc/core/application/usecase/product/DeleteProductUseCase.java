package com.fiap.tc.core.application.usecase.product;

import com.fiap.tc.core.application.usecase.ports.in.product.DeleteProductInputPort;
import com.fiap.tc.core.application.usecase.ports.out.product.DeleteProductOutputPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteProductUseCase implements DeleteProductInputPort {

    private final DeleteProductOutputPort deleteProductOutputPort;

    public DeleteProductUseCase(DeleteProductOutputPort deleteProductOutputPort) {
        this.deleteProductOutputPort = deleteProductOutputPort;
    }

    @Override
    public void delete(UUID idProduct) {
        deleteProductOutputPort.delete(idProduct);
    }
}
