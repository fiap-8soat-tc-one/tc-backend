package com.fiap.tc.core.application.usecase.product;

import com.fiap.tc.core.application.usecase.ports.in.product.RegisterProductImagesInputPort;
import com.fiap.tc.core.application.usecase.ports.out.product.RegisterProductImagesOutputPort;
import com.fiap.tc.core.domain.entities.Product;
import com.fiap.tc.core.domain.entities.ProductImage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RegisterProductImagesUseCase implements RegisterProductImagesInputPort {
    private final RegisterProductImagesOutputPort registerProductImagesOutputPort;

    public RegisterProductImagesUseCase(RegisterProductImagesOutputPort registerProductImagesOutputPort) {
        this.registerProductImagesOutputPort = registerProductImagesOutputPort;
    }

    @Override
    public Product register(UUID idProduct, List<ProductImage> images) {

        return registerProductImagesOutputPort.save(idProduct, images);
    }
}
