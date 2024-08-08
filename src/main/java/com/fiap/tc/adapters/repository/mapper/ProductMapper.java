package com.fiap.tc.adapters.repository.mapper;

import com.fiap.tc.adapters.repository.entity.ProductEntity;
import com.fiap.tc.adapters.repository.mapper.base.MapperEntity;
import com.fiap.tc.core.domain.model.Product;
import com.fiap.tc.core.domain.model.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;

import static com.fiap.tc.adapters.repository.mapper.base.MapperConstants.PRODUCT_IMAGE_MAPPER;
import static org.springframework.util.CollectionUtils.isEmpty;

@Mapper
public interface ProductMapper extends MapperEntity<ProductEntity, Product> {

    @Override
    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "images", source = "productEntity", qualifiedByName = "buildImages")
    @Mapping(target = "idCategory", source = "category.uuid")
    @Mapping(target = "active", source = "audit.active")
    @Mapping(target = "categoryName", source = "category.name")
    Product fromEntity(ProductEntity productEntity);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", source = "id")
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "audit.active", source = "active")
    ProductEntity toEntity(Product product);


    @Named("buildImages")
    default List<ProductImage> buildItems(ProductEntity productEntity) {
        if (!isEmpty(productEntity.getImages())) {
            return productEntity.getImages().stream().map(PRODUCT_IMAGE_MAPPER::fromEntity).toList();
        }
        return Collections.emptyList();
    }


}


