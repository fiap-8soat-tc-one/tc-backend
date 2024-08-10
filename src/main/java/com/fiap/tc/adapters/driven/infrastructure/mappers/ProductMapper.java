package com.fiap.tc.adapters.driven.infrastructure.mappers;

import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.ProductEntity;
import com.fiap.tc.adapters.driven.infrastructure.mappers.base.MapperEntity;
import com.fiap.tc.core.domain.entities.Product;
import com.fiap.tc.core.domain.entities.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;

import static com.fiap.tc.adapters.driven.infrastructure.mappers.base.MapperConstants.PRODUCT_IMAGE_MAPPER;
import static org.springframework.util.CollectionUtils.isEmpty;

@Mapper
public interface ProductMapper extends MapperEntity<ProductEntity, Product> {

    @Override
    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "images", source = "productEntity", qualifiedByName = "buildImages")
    @Mapping(target = "idCategory", source = "category.uuid")
    Product fromEntity(ProductEntity productEntity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", source = "id")
    @Mapping(target = "images", ignore = true)
    ProductEntity toEntity(Product product);


    @Named("buildImages")
    default List<ProductImage> buildItems(ProductEntity productEntity) {
        if (!isEmpty(productEntity.getImages())) {
            return productEntity.getImages().stream().map(PRODUCT_IMAGE_MAPPER::fromEntity).toList();
        }
        return Collections.emptyList();
    }


}


