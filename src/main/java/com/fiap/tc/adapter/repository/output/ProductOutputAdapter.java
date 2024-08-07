package com.fiap.tc.adapter.repository.output;

import com.fiap.tc.adapter.repository.CategoryRepository;
import com.fiap.tc.adapter.repository.ProductRepository;
import com.fiap.tc.adapter.repository.entity.CategoryEntity;
import com.fiap.tc.adapter.repository.entity.ProductEntity;
import com.fiap.tc.core.domain.exception.BadRequestException;
import com.fiap.tc.core.domain.exception.NotFoundException;
import com.fiap.tc.core.domain.model.Product;
import com.fiap.tc.core.port.out.product.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.fiap.tc.adapter.repository.mapper.base.MapperConstants.PRODUCT_MAPPER;
import static java.lang.String.format;

@Service
public class ProductOutputAdapter implements LoadProductOutputPort, RegisterProductOutputPort, ListProductsByCategoryOutputPort, DeleteProductOutputPort, UpdateProductOutputPort {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    public ProductOutputAdapter(ProductRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void delete(UUID idProduct) {
        var productEntityOptional = repository.findByUuid(idProduct);
        productEntityOptional.ifPresent(repository::delete);
    }

    @Override
    public Page<Product> list(UUID idCategory, Pageable pageable) {
        Page<ProductEntity> productsEntity = repository.findByCategoryUuid(idCategory, pageable);
        return productsEntity.map(PRODUCT_MAPPER::fromEntity);
    }

    @Override
    public Product load(UUID idProduct) {
        var productEntityOptional = repository.findByUuid(idProduct);
        if (productEntityOptional.isEmpty()) {
            throw new NotFoundException(format("Product with uuid %s not found!", idProduct));
        }
        return PRODUCT_MAPPER.fromEntity(productEntityOptional.get());
    }

    @Override
    public Product saveOrUpdate(Product product) {

        var productEntityOptional = repository.findByName(product.getName());

        var categoryEntity = getValidCategoryEntity(product.getIdCategory());

        if (productEntityOptional.isPresent()) {
            return updateProduct(product, productEntityOptional.get(), getValidCategoryEntity(product.getIdCategory()));
        }

        return saveProduct(product, categoryEntity);
    }

    private Product saveProduct(Product product, CategoryEntity categoryEntity) {
        var newProductEntity = PRODUCT_MAPPER.toEntity(product);
        newProductEntity.setUuid(UUID.randomUUID());
        newProductEntity.setCategory(categoryEntity);
        newProductEntity.getAudit().setRegisterDate(LocalDateTime.now());

        return PRODUCT_MAPPER.fromEntity(repository.save(newProductEntity));
    }

    private CategoryEntity getValidCategoryEntity(UUID idCategory) {
        var categoryEntityOptional = categoryRepository.findByUuid(idCategory);

        if (categoryEntityOptional.isEmpty()) {
            throw new NotFoundException(format("Category id %s not found!", idCategory));
        }
        return categoryEntityOptional.get();
    }

    private Product updateProduct(Product product, ProductEntity productEntity, CategoryEntity categoryEntity) {
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setCategory(categoryEntity);
        productEntity.setPrice(product.getPrice());
        productEntity.getAudit().setUpdatedDate(LocalDateTime.now());

        return PRODUCT_MAPPER.fromEntity(repository.save(productEntity));
    }

    @Override
    public Product update(Product product) {
        return validateAndUpdate(product);
    }

    private Product validateAndUpdate(Product product) {

        var productEntityOptional = repository.findByUuid(product.getId());

        var productEntityExpectedOptional = repository.findByName(product.getName());

        if (productEntityOptional.isEmpty()) {
            throw new NotFoundException(format("Product with id %s not found!", product.getId()));
        }

        if (productEntityExpectedOptional.isPresent()) {
            throw new BadRequestException(format("Product with expected name %s already exists!", product.getName()));
        }

        return updateProduct(product, productEntityOptional.get(), getValidCategoryEntity(product.getIdCategory()));
    }
}