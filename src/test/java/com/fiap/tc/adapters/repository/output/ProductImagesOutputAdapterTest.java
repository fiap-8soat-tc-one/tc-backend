package com.fiap.tc.adapters.repository.output;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.adapters.repository.ProductImageRepository;
import com.fiap.tc.adapters.repository.ProductRepository;
import com.fiap.tc.adapters.repository.entity.ProductEntity;
import com.fiap.tc.adapters.repository.output.validator.upload.ProductImageValidatorExecutor;
import com.fiap.tc.core.domain.exception.NotFoundException;
import com.fiap.tc.core.domain.model.Product;
import com.fiap.tc.core.domain.model.ProductImage;
import com.fiap.tc.fixture.FixtureTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductImagesOutputAdapterTest extends FixtureTest {

    public static final UUID PRODUCT_ID = UUID.randomUUID();
    @Mock
    private ProductImageValidatorExecutor validator;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductImageRepository productImageRepository;

    @InjectMocks
    private ProductImagesOutputAdapter productImagesOutputAdapter;

    private Product product;

    private ProductEntity productEntity;

    private List<UUID> imagesIds;

    private List<ProductImage> productImagesList;

    @BeforeEach
    public void setUp() {
        product = Fixture.from(Product.class).gimme("valid");
        productEntity = Fixture.from(ProductEntity.class).gimme("with-images");
        imagesIds = List.of(UUID.fromString("440c4c5b-1b65-4fb3-aa8b-6d749e434c50"));
        productImagesList = Fixture.from(ProductImage.class).gimme(1, "valid");
    }

    @Test
    public void uploadImagesTest() {

        when(productRepository.findByUuid(PRODUCT_ID)).thenReturn(Optional.of(productEntity));
        when(productRepository.save(Mockito.any())).thenReturn(productEntity);

        var productResult = productImagesOutputAdapter.save(PRODUCT_ID, productImagesList);

        Assertions.assertNotNull(productResult);
        verify(productRepository).findByUuid(PRODUCT_ID);
        verify(productRepository).save(Mockito.any());

    }


    @Test
    public void deleteProductImagesTest() {
        when(productRepository.findByUuid(PRODUCT_ID)).thenReturn(Optional.of(productEntity));

        productImagesOutputAdapter.delete(PRODUCT_ID, imagesIds);

        verify(productRepository).findByUuid(PRODUCT_ID);
        verify(productImageRepository).deleteById(Mockito.any());

    }

    @Test
    public void launchExceptionOnDeleteProductImagesWhenProductNotExistTest() {
        when(productRepository.findByUuid(PRODUCT_ID)).thenReturn(Optional.empty());

        var assertThrows = assertThrows(NotFoundException.class, () -> productImagesOutputAdapter
                .delete(PRODUCT_ID, imagesIds));

        verify(productRepository).findByUuid(PRODUCT_ID);
        assertTrue(assertThrows.getMessage().contains("not found"));


    }

    @Test
    public void doNothingOnDeleteProductImagesWhenProductNotContainsImagesTest() {
        productEntity.setImages(Collections.emptyList());
        when(productRepository.findByUuid(PRODUCT_ID)).thenReturn(Optional.of(productEntity));

        productImagesOutputAdapter.delete(PRODUCT_ID, imagesIds);

        verify(productRepository).findByUuid(PRODUCT_ID);

    }


}