package com.fiap.tc.adapter.repository.output;

import com.fiap.tc.adapter.repository.ProductImageRepository;
import com.fiap.tc.adapter.repository.ProductRepository;
import com.fiap.tc.adapter.repository.entity.ProductEntity;
import com.fiap.tc.adapter.repository.entity.ProductImageEntity;
import com.fiap.tc.adapter.repository.entity.embeddable.Audit;
import com.fiap.tc.adapter.repository.mapper.base.MapperConstants;
import com.fiap.tc.common.config.UploadConfig;
import com.fiap.tc.core.domain.exception.BadRequestException;
import com.fiap.tc.core.domain.exception.NotFoundException;
import com.fiap.tc.core.domain.model.Product;
import com.fiap.tc.core.domain.model.ProductImage;
import com.fiap.tc.core.port.out.product.DeleteProductImagesOutputPort;
import com.fiap.tc.core.port.out.product.RegisterProductImagesOutputPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class ProductImagesOutputAdapter implements RegisterProductImagesOutputPort, DeleteProductImagesOutputPort {

    private final UploadConfig uploadConfig;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public ProductImagesOutputAdapter(UploadConfig uploadConfig,
                                      ProductRepository productRepository,
                                      ProductImageRepository productImageRepository) {
        this.uploadConfig = uploadConfig;
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

        validateImages(productEntity, images);

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

    private void validateImages(ProductEntity productEntity, List<ProductImage> images) {
        validateMaxImagesByProduct(productEntity, images);
        validateImagesMaxLengthAndMimeTypes(images);
    }

    private void validateImagesMaxLengthAndMimeTypes(List<ProductImage> images) {
        List<String> errors = new ArrayList<>();
        images.forEach(productImage -> validateImage(productImage, errors));
        if (!errors.isEmpty()) {
            String errorMessage = errors.stream().map(String::valueOf).collect(Collectors.joining(","));
            throw new BadRequestException(errorMessage, errors);
        }
    }

    private void validateMaxImagesByProduct(ProductEntity productEntity, List<ProductImage> images) {
        if (!isEmpty(productEntity.getImages())) {
            var totalImagesPreview = productEntity.getImages().size() + images.size();
            if (totalImagesPreview > uploadConfig.getMaxProductImages()) {
                throw new BadRequestException(format("Product id %s max product images reached %d",
                        productEntity.getUuid(),
                        uploadConfig.getMaxProductImages()));
            }
        }
    }

    private void validateImage(ProductImage productImage, List<String> errors) {
        var imageBase64 = productImage.getImage().split(",");
        var mimeType = imageBase64[0];
        var imageBase64Length = imageBase64[1].length();

        if (!uploadConfig.getMimeTypes().contains(mimeType)) {
            errors.add(format("Invalid image extension for '%s' allowed %s", productImage.getName(), uploadConfig.getMimeTypes()));
        }
        if (imageBase64Length > uploadConfig.getMaxLength()) {
            errors.add(format("Invalid image for '%s' max length %s KB", productImage.getName(),
                    uploadConfig.getMaxLength() / 1000));
        }

    }

}
