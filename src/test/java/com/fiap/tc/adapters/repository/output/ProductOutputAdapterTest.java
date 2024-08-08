package com.fiap.tc.adapters.repository.output;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.adapters.driven.infrastructure.output.ProductOutputAdapter;
import com.fiap.tc.adapters.driven.infrastructure.persistence.repositories.CategoryRepository;
import com.fiap.tc.adapters.driven.infrastructure.persistence.repositories.ProductRepository;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entity.CategoryEntity;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entity.ProductEntity;
import com.fiap.tc.core.domain.exception.BadRequestException;
import com.fiap.tc.core.domain.exception.NotFoundException;
import com.fiap.tc.core.domain.model.Product;
import com.fiap.tc.fixture.FixtureTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductOutputAdapterTest extends FixtureTest {

    public static final UUID PRODUCT_ID = UUID.randomUUID();
    public static final UUID CATEGORY_ID = UUID.randomUUID();

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductOutputAdapter productOutputAdapter;

    private Product product;

    private ProductEntity productEntity;

    private CategoryEntity categoryEntity;

    private Pageable pageable;

    @BeforeEach
    public void setUp() {
        product = Fixture.from(Product.class).gimme("valid");
        productEntity = Fixture.from(ProductEntity.class).gimme("valid");
        categoryEntity = Fixture.from(CategoryEntity.class).gimme("valid");
        pageable = Mockito.mock(Pageable.class);

    }

    @Test
    public void deleteProductTest() {
        when(productRepository.findByUuid(PRODUCT_ID)).thenReturn(Optional.of(productEntity));

        productOutputAdapter.delete(PRODUCT_ID);

        verify(productRepository).findByUuid(PRODUCT_ID);
        verify(productRepository).delete(productEntity);

    }

    @Test
    public void listProductsByCategoryTest() {
        final var productEntities = new PageImpl<ProductEntity>(List.of(productEntity));

        when(productRepository.findByCategoryUuid(CATEGORY_ID, pageable)).thenReturn(productEntities);

        var productsPageable = productOutputAdapter.list(CATEGORY_ID, pageable);

        assertEquals(1, productsPageable.getSize());
        verify(productRepository).findByCategoryUuid(CATEGORY_ID, pageable);
    }

    @Test
    public void loadProductTest() {
        when(productRepository.findByUuid(PRODUCT_ID)).thenReturn(Optional.of(productEntity));

        var productResult = productOutputAdapter.load(PRODUCT_ID);

        assertNotNull(productResult);
        verify(productRepository).findByUuid(PRODUCT_ID);
    }

    @Test
    public void launchExceptionOnLoadProductWhenProductNotExistsTest() {
        when(productRepository.findByUuid(PRODUCT_ID)).thenReturn(Optional.empty());

        var assertThrows = Assertions.assertThrows(NotFoundException.class,
                () -> productOutputAdapter.load(PRODUCT_ID));

        assertTrue(assertThrows.getMessage().contains("not found"));
    }

    @Test
    public void saveProductTest() {
        when(productRepository.findByName(Mockito.anyString())).thenReturn(Optional.empty());
        when(categoryRepository.findByUuid(Mockito.any())).thenReturn(Optional.of(categoryEntity));
        when(productRepository.save(Mockito.any())).thenReturn(productEntity);

        var productResult = productOutputAdapter.saveOrUpdate(product);

        assertNotNull(productResult);
        verify(productRepository).findByName(Mockito.anyString());
        verify(categoryRepository).findByUuid(Mockito.any());
        verify(productRepository).save(Mockito.any());
    }

    @Test
    public void updateProductTest() {
        when(productRepository.findByName(Mockito.anyString())).thenReturn(Optional.of(productEntity));
        when(categoryRepository.findByUuid(Mockito.any())).thenReturn(Optional.of(categoryEntity));
        when(productRepository.save(Mockito.any())).thenReturn(productEntity);

        var productResult = productOutputAdapter.saveOrUpdate(product);

        assertNotNull(productResult);
        verify(productRepository).findByName(Mockito.anyString());
        verify(categoryRepository).findByUuid(Mockito.any());
        verify(productRepository).save(Mockito.any());
    }

    @Test
    public void launchExceptionOnSaveProductWhenCategoryNotExistsTest() {
        when(productRepository.findByName(Mockito.anyString())).thenReturn(Optional.empty());
        when(categoryRepository.findByUuid(Mockito.any())).thenReturn(Optional.empty());

        var assertThrows = Assertions.assertThrows(NotFoundException.class,
                () -> productOutputAdapter.saveOrUpdate(product));

        assertTrue(assertThrows.getMessage().contains("not found"));

    }

    @Test
    public void updateProductNameTest() {

        when(productRepository.findByName(product.getName())).thenReturn(Optional.empty());
        when(productRepository.findByUuid(product.getId())).thenReturn(Optional.of(productEntity));
        when(categoryRepository.findByUuid(Mockito.any())).thenReturn(Optional.of(categoryEntity));
        when(productRepository.save(Mockito.any())).thenReturn(productEntity);

        var productResult = productOutputAdapter.update(product);

        assertNotNull(productResult);
        verify(productRepository).findByName(Mockito.anyString());
        verify(productRepository).findByUuid(Mockito.any());
        verify(categoryRepository).findByUuid(Mockito.any());
        verify(productRepository).save(Mockito.any());
    }

    @Test
    public void launchExceptionOnUpdateProductNameWhenProductByUuidNotExistsTest() {

        when(productRepository.findByName(product.getName())).thenReturn(Optional.empty());
        when(productRepository.findByUuid(product.getId())).thenReturn(Optional.empty());


        var assertThrows = Assertions.assertThrows(NotFoundException.class,
                () -> productOutputAdapter.update(product));

        assertTrue(assertThrows.getMessage().contains("not found"));


    }


    @Test
    public void launchExceptionOnUpdateProductNameWhenExpectedNameAlreadyExistsTest() {

        when(productRepository.findByName(product.getName())).thenReturn(Optional.of(productEntity));
        when(productRepository.findByUuid(product.getId())).thenReturn(Optional.of(productEntity));


        var assertThrows = Assertions.assertThrows(BadRequestException.class,
                () -> productOutputAdapter.update(product));

        assertTrue(assertThrows.getMessage().contains("already exists"));


    }


}