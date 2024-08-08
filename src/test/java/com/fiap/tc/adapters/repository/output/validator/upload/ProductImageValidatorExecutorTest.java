package com.fiap.tc.adapters.repository.output.validator.upload;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.adapters.driven.infrastructure.output.validator.upload.ProductImageValidator;
import com.fiap.tc.adapters.driven.infrastructure.output.validator.upload.ProductImageValidatorExecutor;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entity.ProductEntity;
import com.fiap.tc.core.domain.exception.BadRequestException;
import com.fiap.tc.core.domain.model.ProductImage;
import com.fiap.tc.fixture.FixtureTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductImageValidatorExecutorTest extends FixtureTest {

    @Mock
    private ProductImageValidator imageExtensionValidator;
    @Mock
    private ProductImageValidator imageMaxLengthValidator;
    @Mock
    private ProductImageValidator productImagesMaxSizeValidator;

    private ProductImageValidatorExecutor productImageValidatorExecutor;

    private ProductEntity productEntity;

    private List<ProductImage> productImageList;

    @BeforeEach
    public void setUp() {

        productImageValidatorExecutor = new ProductImageValidatorExecutor(imageExtensionValidator,
                imageMaxLengthValidator, productImagesMaxSizeValidator);

        productEntity = Fixture.from(ProductEntity.class).gimme("valid");

        productImageList = Fixture.from(ProductImage.class).gimme(1, "valid");
    }

    @Test
    public void execute_validator_test() {

        productImageValidatorExecutor.execute(productEntity, productImageList);

        verify(imageExtensionValidator).execute(any(), any());
        verify(imageMaxLengthValidator).execute(any(), any());
        verify(productImagesMaxSizeValidator).execute(any(), any());
    }


    @Test
    public void launch_exception_on_validator_error_test() {
        doAnswer(invocation -> {
            List<String> errors = invocation.getArgument(1);
            errors.add("Sample error message");
            return null;
        }).when(imageExtensionValidator).execute(any(), any());


        var assertThrows = assertThrows(BadRequestException.class,
                () -> productImageValidatorExecutor.execute(productEntity, productImageList));

        assertTrue(assertThrows.getMessage().contains("Sample error message"));
    }


}