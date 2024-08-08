package com.fiap.tc.adapters.driven.infrastructure.outputs.validators.upload;

import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.ProductEntity;
import com.fiap.tc.core.domain.entities.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageValidatorWrapper {
    private Integer uploadListSize;
    private ProductEntity productEntity;
    private ProductImage productImage;
}
