package com.fiap.tc.core.usecase.product;

import com.fiap.tc.core.port.in.product.DeleteProductInputPort;
import com.fiap.tc.core.port.out.product.DeleteProductOutputPort;
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
