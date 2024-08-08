package com.fiap.tc.core.application.usecase.product;

import com.fiap.tc.core.application.ports.in.product.RegisterProductImagesInputPort;
import com.fiap.tc.core.domain.entities.Product;
import com.fiap.tc.adapters.driver.presentation.requests.RegisterProductImagesRequest;
import com.fiap.tc.core.application.ports.out.product.RegisterProductImagesOutputPort;
import org.springframework.stereotype.Service;

import static com.fiap.tc.adapters.driven.infrastructure.persistence.mappers.base.MapperConstants.PRODUCT_IMAGE_REQUEST_MAPPER;

@Service
public class RegisterProductImagesUseCase implements RegisterProductImagesInputPort {
    private final RegisterProductImagesOutputPort registerProductImagesOutputPort;

    public RegisterProductImagesUseCase(RegisterProductImagesOutputPort registerProductImagesOutputPort) {
        this.registerProductImagesOutputPort = registerProductImagesOutputPort;
    }

    @Override
    public Product register(RegisterProductImagesRequest request) {
        var productImages = request.getImages().stream().map(PRODUCT_IMAGE_REQUEST_MAPPER::toEntity).toList();
        return registerProductImagesOutputPort.save(request.getIdProduct(), productImages);
    }
}
