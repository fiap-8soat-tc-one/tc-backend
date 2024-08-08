package com.fiap.tc.adapters.repository.output.validator.upload.concrete;

import com.fiap.tc.adapters.repository.output.validator.upload.ProductImageValidator;
import com.fiap.tc.adapters.repository.output.validator.upload.ProductImageValidatorWrapper;
import com.fiap.tc.common.config.UploadConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.String.format;

@Component
@Qualifier("imageExtensionValidator")
public class ImageExtensionValidator implements ProductImageValidator {

    private final UploadConfig uploadConfig;

    public ImageExtensionValidator(UploadConfig uploadConfig) {
        this.uploadConfig = uploadConfig;
    }

    @Override
    public void execute(ProductImageValidatorWrapper wrapper, List<String> errors) {
        var productImage = wrapper.getProductImage();
        var mimeType = productImage.getImage().split(",")[0];

        if (!uploadConfig.getMimeTypes().contains(mimeType)) {
            errors.add(format("Invalid image extension for '%s' allowed %s", productImage.getName(),
                    uploadConfig.getMimeTypes()));
        }
    }
}
