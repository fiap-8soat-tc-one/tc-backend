package com.fiap.tc.adapters.repository.output.validator.upload.concrete;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.adapters.driven.infrastructure.output.validator.upload.concrete.ImageMaxLengthValidator;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.ProductEntity;
import com.fiap.tc.adapters.driven.infrastructure.output.validator.upload.ProductImageValidatorWrapper;
import com.fiap.tc.common.config.UploadConfig;
import com.fiap.tc.core.domain.exception.BadRequestException;
import com.fiap.tc.core.domain.model.ProductImage;
import com.fiap.tc.fixture.FixtureTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ImageMaxLengthValidatorTest extends FixtureTest {

    private UploadConfig uploadConfig;

    private ImageMaxLengthValidator imageMaxLengthValidator;

    private ProductImageValidatorWrapper wrapper;
    private List<String> errors;
    private ProductEntity productEntity;
    private List<ProductImage> productImageList;
    private List<ProductImage> productWithInvalidImageList;

    @BeforeEach
    public void setUp() {
        productEntity = Fixture.from(ProductEntity.class).gimme("with-images");
        productImageList = Fixture.from(ProductImage.class).gimme(1, "valid");
        productWithInvalidImageList = Fixture.from(ProductImage.class).gimme(1, "invalid");
        errors = new ArrayList<>();
        wrapper = ProductImageValidatorWrapper.builder()
                .productEntity(productEntity)
                .uploadListSize(productImageList.size())
                .productImage(productImageList.get(0))
                .build();


        uploadConfig = Mockito.spy(UploadConfig.builder()
                .maxProductImages(1)
                .maxLength(2000)
                .mimeTypes(List.of("data:image/gif;base64"))
                .build());
    }

    @Test
    public void getErrorMessageWhenMaxLengthReachedTest() {
        imageMaxLengthValidator = new ImageMaxLengthValidator(uploadConfig);
        imageMaxLengthValidator.execute(wrapper, errors);
        assertEquals(1, errors.size());
    }

    @Test
    public void launchErrorOnValidateWhenInvalidImageTest() {
        wrapper = ProductImageValidatorWrapper.builder()
                .productEntity(productEntity)
                .uploadListSize(productWithInvalidImageList.size())
                .productImage(productWithInvalidImageList.get(0))
                .build();

        imageMaxLengthValidator = new ImageMaxLengthValidator(uploadConfig);

        var assertThrows = Assertions.assertThrows(BadRequestException.class,
                () -> imageMaxLengthValidator.execute(wrapper, errors));


        assertTrue(assertThrows.getMessage().contains("Invalid image"));
    }

    @Test
    public void executeMaxLengthValidatorWhenValidLengthTest() {

        uploadConfig.setMaxLength(100000);
        imageMaxLengthValidator = new ImageMaxLengthValidator(uploadConfig);

        imageMaxLengthValidator.execute(wrapper, errors);

        assertEquals(0, errors.size());
    }

}