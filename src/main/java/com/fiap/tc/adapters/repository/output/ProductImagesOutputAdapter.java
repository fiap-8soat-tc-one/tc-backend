package com.fiap.tc.adapters.repository.output;

import com.fiap.tc.adapters.repository.ProductImageRepository;
import com.fiap.tc.adapters.repository.ProductRepository;
import com.fiap.tc.adapters.repository.entity.ProductEntity;
import com.fiap.tc.adapters.repository.entity.ProductImageEntity;
import com.fiap.tc.adapters.repository.entity.embeddable.Audit;
import com.fiap.tc.adapters.repository.mapper.base.MapperConstants;
import com.fiap.tc.adapters.repository.output.validator.upload.ProductImageValidatorExecutor;
import com.fiap.tc.core.domain.exception.NotFoundException;
import com.fiap.tc.core.domain.model.Product;
import com.fiap.tc.core.domain.model.ProductImage;
import com.fiap.tc.core.port.out.product.DeleteProductImagesOutputPort;
import com.fiap.tc.core.port.out.product.RegisterProductImagesOutputPort;
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
public class ProductImagesOutputAdapter implements RegisterProductImagesOutputPort, DeleteProductImagesOutputPort {

    private final ProductImageValidatorExecutor validator;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public ProductImagesOutputAdapter(ProductImageValidatorExecutor productImageValidatorProcessor,
                                      ProductRepository productRepository,
                                      ProductImageRepository productImageRepository) {
        this.validator = productImageValidatorProcessor;
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
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

    @Override
    public Product save(UUID idProduct, List<ProductImage> images) {

        var productEntity = validateAndGetProduct(idProduct);

        validator.execute(productEntity, images);

        return uploadImagesAndSave(productEntity, images);

    }

    private ProductEntity validateAndGetProduct(UUID idProduct) {
        var productEntityOptional = productRepository.findByUuid(idProduct);

        if (productEntityOptional.isEmpty()) {
            throw new NotFoundException(format("Product with id %s not found!", idProduct));
        }
        return productEntityOptional.get();
    }

    private Product uploadImagesAndSave(ProductEntity productEntity, List<ProductImage> images) {

        images.forEach(productImage -> {
            productEntity.getImages().add(uploadImage(productImage, productEntity));
        });

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
