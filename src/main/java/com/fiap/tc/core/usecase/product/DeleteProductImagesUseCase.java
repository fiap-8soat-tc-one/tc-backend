package com.fiap.tc.core.usecase.product;

import com.fiap.tc.core.domain.model.Product;
import com.fiap.tc.core.domain.requests.DeleteProductImagesRequest;
import com.fiap.tc.core.port.in.product.DeleteProductImagesInputPort;
import com.fiap.tc.core.port.out.product.DeleteProductImagesOutputPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteProductImagesUseCase implements DeleteProductImagesInputPort {
    private final DeleteProductImagesOutputPort deleteProductImagesOutputPort;

    public DeleteProductImagesUseCase(DeleteProductImagesOutputPort deleteProductImagesOutputPort) {
        this.deleteProductImagesOutputPort = deleteProductImagesOutputPort;
    }

    @Override
    public Product register(UUID idProduct, DeleteProductImagesRequest request) {
        return deleteProductImagesOutputPort.delete(idProduct, request.getImages());
    }
}
