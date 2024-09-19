package com.fiap.tc.core.application.usecase.product;

import com.fiap.tc.core.application.usecase.ports.in.product.DeleteProductImagesInputPort;
import com.fiap.tc.core.application.usecase.ports.out.product.DeleteProductImagesOutputPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeleteProductImagesUseCase implements DeleteProductImagesInputPort {
    private final DeleteProductImagesOutputPort deleteProductImagesOutputPort;

    public DeleteProductImagesUseCase(DeleteProductImagesOutputPort deleteProductImagesOutputPort) {
        this.deleteProductImagesOutputPort = deleteProductImagesOutputPort;
    }

    @Override
    public void delete(UUID idProduct, List<UUID> productImagesWithIds) {
        deleteProductImagesOutputPort.delete(idProduct, productImagesWithIds);
    }
}
