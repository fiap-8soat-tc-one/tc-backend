package com.fiap.tc.adapter.repository.output.validator.upload.concrete;

import com.fiap.tc.adapter.repository.output.validator.upload.ProductImageValidator;
import com.fiap.tc.adapter.repository.output.validator.upload.ProductImageValidatorWrapper;
import com.fiap.tc.common.config.UploadConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.String.format;

@Component
@Qualifier("imageMaxLengthValidator")
public class ImageMaxLengthValidator implements ProductImageValidator {
    private final UploadConfig uploadConfig;

    public ImageMaxLengthValidator(UploadConfig uploadConfig) {
        this.uploadConfig = uploadConfig;
    }

    @Override
    public void execute(ProductImageValidatorWrapper wrapper, List<String> errors) {
        var productImage = wrapper.getProductImage();
        var imageBase64Length = productImage.getImage().split(",")[1].length();

        if (imageBase64Length > uploadConfig.getMaxLength()) {
            errors.add(format("Invalid image for '%s' max length %s KB", productImage.getName(),
                    uploadConfig.getMaxLength() / 1000));
        }
    }
}
