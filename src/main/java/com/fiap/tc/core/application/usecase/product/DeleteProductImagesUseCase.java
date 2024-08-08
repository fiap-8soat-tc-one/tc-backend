package com.fiap.tc.core.application.usecase.product;

import com.fiap.tc.core.application.ports.in.product.DeleteProductImagesInputPort;
import com.fiap.tc.adapters.driver.presentation.requests.DeleteProductImagesRequest;
import com.fiap.tc.core.application.ports.out.product.DeleteProductImagesOutputPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductImagesUseCase implements DeleteProductImagesInputPort {
    private final DeleteProductImagesOutputPort deleteProductImagesOutputPort;

    public DeleteProductImagesUseCase(DeleteProductImagesOutputPort deleteProductImagesOutputPort) {
        this.deleteProductImagesOutputPort = deleteProductImagesOutputPort;
    }

    @Override
    public void delete(DeleteProductImagesRequest request) {
        deleteProductImagesOutputPort.delete(request.getIdProduct(), request.getImages());
    }
}
