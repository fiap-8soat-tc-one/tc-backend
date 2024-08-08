package com.fiap.tc.adapters.repository.output.validator.upload.concrete;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.adapters.repository.entity.ProductEntity;
import com.fiap.tc.adapters.repository.output.validator.upload.ProductImageValidatorWrapper;
import com.fiap.tc.common.config.UploadConfig;
import com.fiap.tc.core.domain.model.ProductImage;
import com.fiap.tc.fixture.FixtureTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ImageExtensionValidatorTest extends FixtureTest {

    private UploadConfig uploadConfig;

    private ImageExtensionValidator imageExtensionValidator;

    private ProductImageValidatorWrapper wrapper;
    private List<String> errors;
    private ProductEntity productEntity;
    private List<ProductImage> productImageList;

    @BeforeEach
    public void setUp() {
        productEntity = Fixture.from(ProductEntity.class).gimme("with-images");
        productImageList = Fixture.from(ProductImage.class).gimme(1, "valid");
        errors = new ArrayList<>();
        wrapper = ProductImageValidatorWrapper.builder()
                .productEntity(productEntity)
                .uploadListSize(productImageList.size())
                .productImage(productImageList.get(0))
                .build();


        uploadConfig = Mockito.spy(UploadConfig.builder()
                .maxProductImages(1)
                .maxLength(20000)
                .mimeTypes(List.of("data:image/gif;base64"))
                .build());
    }

    @Test
    public void getErrorMessageImageExtensionWhenInvalidExtensionTest() {
        imageExtensionValidator = new ImageExtensionValidator(uploadConfig);
        imageExtensionValidator.execute(wrapper, errors);
        assertEquals(1, errors.size());
    }

    @Test
    public void executeImageExtensionValidatorWhenValidExtensionTest() {
        uploadConfig.setMimeTypes(List.of("data:image/jpeg;base64" ));
        imageExtensionValidator = new ImageExtensionValidator(uploadConfig);

        imageExtensionValidator.execute(wrapper, errors);

        assertEquals(0, errors.size());
    }

}