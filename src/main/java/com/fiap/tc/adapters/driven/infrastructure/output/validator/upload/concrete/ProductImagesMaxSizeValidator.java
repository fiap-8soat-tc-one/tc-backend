package com.fiap.tc.adapters.repository.output.validator.upload.concrete;

import com.fiap.tc.adapters.repository.output.validator.upload.ProductImageValidator;
import com.fiap.tc.adapters.repository.output.validator.upload.ProductImageValidatorWrapper;
import com.fiap.tc.common.config.UploadConfig;
import com.fiap.tc.core.domain.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.String.format;
import static org.springframework.util.CollectionUtils.isEmpty;

@Component
@Qualifier("productImagesMaxSizeValidator")
public class ProductImagesMaxSizeValidator implements ProductImageValidator {

    private final UploadConfig uploadConfig;

    public ProductImagesMaxSizeValidator(UploadConfig uploadConfig) {
        this.uploadConfig = uploadConfig;
    }

    @Override
    public void execute(ProductImageValidatorWrapper wrapper, List<String> errors) {
        var productEntityImages = wrapper.getProductEntity().getImages();
        if (!isEmpty(productEntityImages)) {
            var totalImagesPreview = productEntityImages.size() + wrapper.getUploadListSize();
            if (totalImagesPreview > uploadConfig.getMaxProductImages()) {
                throw new BadRequestException(format("Product id %s max product images reached %d",
                        wrapper.getProductEntity().getUuid(),
                        uploadConfig.getMaxProductImages()));
            }
        }
    }
}
