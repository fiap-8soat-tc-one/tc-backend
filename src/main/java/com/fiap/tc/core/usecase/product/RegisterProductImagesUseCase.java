package com.fiap.tc.core.usecase.product;

import com.fiap.tc.core.domain.model.Product;
import com.fiap.tc.core.domain.requests.RegisterProductImagesRequest;
import com.fiap.tc.core.port.in.product.RegisterProductImagesInputPort;
import com.fiap.tc.core.port.out.product.RegisterProductImagesOutputPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.fiap.tc.adapter.repository.mapper.base.MapperConstants.PRODUCT_IMAGE_REQUEST_MAPPER;

@Service
public class RegisterProductImagesUseCase implements RegisterProductImagesInputPort {
    private final RegisterProductImagesOutputPort registerProductImagesOutputPort;

    public RegisterProductImagesUseCase(RegisterProductImagesOutputPort registerProductImagesOutputPort) {
        this.registerProductImagesOutputPort = registerProductImagesOutputPort;
    }

    @Override
    public Product register(UUID idProduct, RegisterProductImagesRequest request) {
        var productImages = request.getImages().stream().map(PRODUCT_IMAGE_REQUEST_MAPPER::toEntity).toList();
        return registerProductImagesOutputPort.save(idProduct, productImages);
    }
}
