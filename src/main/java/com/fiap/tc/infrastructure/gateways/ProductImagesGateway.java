package com.fiap.tc.infrastructure.gateways;

import com.fiap.tc.application.gateways.ProductImagesGatewaySpec;
import com.fiap.tc.domain.entities.Product;
import com.fiap.tc.domain.entities.ProductImage;
import com.fiap.tc.domain.exceptions.NotFoundException;
import com.fiap.tc.infrastructure.gateways.mappers.base.MapperConstants;
import com.fiap.tc.infrastructure.gateways.validators.upload.ProductImageValidatorExecutor;
import com.fiap.tc.infrastructure.persistence.entities.ProductEntity;
import com.fiap.tc.infrastructure.persistence.entities.ProductImageEntity;
import com.fiap.tc.infrastructure.persistence.entities.embeddable.Audit;
import com.fiap.tc.infrastructure.persistence.repositories.ProductImageRepository;
import com.fiap.tc.infrastructure.persistence.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class ProductImagesGateway implements ProductImagesGatewaySpec {
    private final ProductImageValidatorExecutor validator;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public ProductImagesGateway(ProductImageValidatorExecutor validator, ProductRepository productRepository,
                                ProductImageRepository productImageRepository) {
        this.validator = validator;
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }

    @Override
    public Product register(UUID idProduct, List<ProductImage> images) {
        var productEntity = validateAndGetProduct(idProduct);

        validator.execute(productEntity, images);

        return uploadImagesAndSave(productEntity, images);
    }

    @Override
    public void delete(UUID idProduct, List<UUID> productImagesWithIds) {

        var productEntity = validateAndGetProduct(idProduct);
        if (!isEmpty(productEntity.getImages())) {

            Map<UUID, ProductImageEntity> mapProductImages = productEntity.getImages().stream()
                    .collect(Collectors.toMap(ProductImageEntity::getUuid, Function.identity()));

            productImagesWithIds.forEach(imageId -> {
                var productImageEntity = mapProductImages.get(imageId);
                Optional.ofNullable(productImageEntity).ifPresent(productImage -> productImageRepository
                        .deleteById(productImage.getId()));
            });
        }
    }

    private ProductEntity validateAndGetProduct(UUID idProduct) {
        var productEntityOptional = productRepository.findByUuid(idProduct);

        if (productEntityOptional.isEmpty()) {
            throw new NotFoundException(format("Product with id %s not found!", idProduct));
        }
        return productEntityOptional.get();
    }

    private Product uploadImagesAndSave(ProductEntity productEntity, List<ProductImage> images) {

        for (ProductImage productImage : images) {
            productEntity.getImages().add(uploadImage(productImage, productEntity));
        }

        return MapperConstants.PRODUCT_MAPPER.fromEntity(productRepository.save(productEntity));
    }

    private ProductImageEntity uploadImage(ProductImage productImage, ProductEntity productEntity) {
        ProductImageEntity productImageEntity = MapperConstants.PRODUCT_IMAGE_MAPPER.toEntity(productImage);
        productImageEntity.setUuid(UUID.randomUUID());
        productImageEntity.setProduct(productEntity);
        productImageEntity.setAudit(Audit.builder().active(true).registerDate(LocalDateTime.now()).build());

        return productImageEntity;
    }
}
