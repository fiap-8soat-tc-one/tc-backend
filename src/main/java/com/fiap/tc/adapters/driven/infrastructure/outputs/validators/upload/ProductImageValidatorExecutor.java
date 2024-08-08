package com.fiap.tc.adapters.driven.infrastructure.outputs.validators.upload;

import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.ProductEntity;
import com.fiap.tc.core.domain.exception.BadRequestException;
import com.fiap.tc.core.domain.model.ProductImage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductImageValidatorExecutor {

    private final ProductImageValidator imageExtensionValidator;
    private final ProductImageValidator imageMaxLengthValidator;
    private final ProductImageValidator productImagesMaxSizeValidator;

    private List<ProductImageValidator> productImageValidatorList;

    public ProductImageValidatorExecutor(ProductImageValidator imageExtensionValidator,
                                         ProductImageValidator imageMaxLengthValidator,
                                         ProductImageValidator productImagesMaxSizeValidator) {
        this.imageExtensionValidator = imageExtensionValidator;
        this.imageMaxLengthValidator = imageMaxLengthValidator;
        this.productImagesMaxSizeValidator = productImagesMaxSizeValidator;

        initValidators();
    }

    private void initValidators() {
        productImageValidatorList = new LinkedList<>();
        productImageValidatorList.add(productImagesMaxSizeValidator);
        productImageValidatorList.add(imageExtensionValidator);
        productImageValidatorList.add(imageMaxLengthValidator);
    }

    public void execute(ProductEntity productEntity, List<ProductImage> images) {
        List<String> errors = new ArrayList<>();

        images.forEach(image -> {
            var wrapper = ProductImageValidatorWrapper
                    .builder()
                    .productEntity(productEntity)
                    .uploadListSize(images.size())
                    .productImage(image)
                    .build();
            productImageValidatorList.forEach(validator -> validator.execute(wrapper, errors));
        });


        if (!errors.isEmpty()) {
            String errorMessage = errors.stream().map(String::valueOf).collect(Collectors.joining(","));
            throw new BadRequestException(errorMessage, errors);
        }
    }

}
