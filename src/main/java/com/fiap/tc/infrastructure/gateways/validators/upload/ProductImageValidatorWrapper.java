package com.fiap.tc.infrastructure.gateways.validators.upload;

import com.fiap.tc.infrastructure.persistence.entities.ProductEntity;
import com.fiap.tc.domain.entities.ProductImage;
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
