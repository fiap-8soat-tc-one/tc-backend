package com.fiap.tc.infrastructure.gateways;

import com.fiap.tc.application.gateways.ProductGatewaySpec;
import com.fiap.tc.domain.entities.Product;
import com.fiap.tc.domain.exceptions.BadRequestException;
import com.fiap.tc.domain.exceptions.NotFoundException;
import com.fiap.tc.infrastructure.persistence.entities.CategoryEntity;
import com.fiap.tc.infrastructure.persistence.entities.ProductEntity;
import com.fiap.tc.infrastructure.persistence.entities.embeddable.Audit;
import com.fiap.tc.infrastructure.persistence.repositories.CategoryRepository;
import com.fiap.tc.infrastructure.persistence.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.fiap.tc.infrastructure.gateways.mappers.base.MapperConstants.PRODUCT_MAPPER;
import static java.lang.String.format;

@Service
public class ProductGateway implements ProductGatewaySpec {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    public ProductGateway(ProductRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product load(UUID id) {
        var productEntityOptional = repository.findByUuid(id);
        if (productEntityOptional.isEmpty()) {
            throw new NotFoundException(format("Product with uuid %s not found!", id));
        }
        return PRODUCT_MAPPER.fromEntity(productEntityOptional.get());
    }

    @Override
    public Product register(Product product) {
        var productEntityOptional = repository.findByName(product.getName());

        var categoryEntity = getValidCategoryEntity(product.getIdCategory());

        if (productEntityOptional.isPresent()) {
            return updateProduct(product, productEntityOptional.get(), categoryEntity);
        }

        return saveProduct(product, categoryEntity);
    }

    @Override
    public Product update(Product product) {
        return updateProduct(product, validate(product), getValidCategoryEntity(product.getIdCategory()));
    }

    @Override
    public Page<Product> list(UUID idCategory, Pageable pageable) {
        Page<ProductEntity> productsEntity = repository.findByCategoryUuid(idCategory, pageable);
        return productsEntity.map(PRODUCT_MAPPER::fromEntity);
    }

    @Override
    public void delete(UUID id) {
        var productEntityOptional = repository.findByUuid(id);
        productEntityOptional.ifPresent(repository::delete);
    }

    private Product saveProduct(Product product, CategoryEntity categoryEntity) {
        var newProductEntity = PRODUCT_MAPPER.toEntity(product);
        newProductEntity.setUuid(UUID.randomUUID());
        newProductEntity.setCategory(categoryEntity);
        newProductEntity.setAudit(Audit.builder().active(true).registerDate(LocalDateTime.now()).build());

        return PRODUCT_MAPPER.fromEntity(repository.save(newProductEntity));
    }

    private CategoryEntity getValidCategoryEntity(UUID idCategory) {
        var categoryEntityOptional = categoryRepository.findByUuid(idCategory);

        if (categoryEntityOptional.isEmpty()) {
            throw new NotFoundException(format("Category id %s not found!", idCategory));
        }
        return categoryEntityOptional.get();
    }

    private ProductEntity validate(Product product) {
        var productEntityOptional = repository.findByUuid(product.getId());

        var productEntityExpectedOptional = repository.findByName(product.getName());

        if (productEntityOptional.isEmpty()) {
            throw new NotFoundException(format("Product with id %s not found!", product.getId()));
        }

        var productEntity = productEntityOptional.get();
        var notSameProductName = !(productEntity.getName().equals(product.getName()));

        var hasInvalidProductName = notSameProductName && productEntityExpectedOptional.isPresent();

        if (hasInvalidProductName) {
            throw new BadRequestException(format("Product with expected name %s already exists!", product.getName()));
        }

        return productEntityOptional.get();
    }

    private Product updateProduct(Product product, ProductEntity productEntity, CategoryEntity categoryEntity) {
        productEntity.setDescription(product.getDescription());
        productEntity.setCategory(categoryEntity);
        productEntity.setPrice(product.getPrice());
        productEntity.getAudit().setUpdatedDate(LocalDateTime.now());

        return PRODUCT_MAPPER.fromEntity(repository.save(productEntity));
    }

}
