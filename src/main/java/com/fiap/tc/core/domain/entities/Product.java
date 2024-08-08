package com.fiap.tc.core.domain.entities;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Product {
    private UUID id;
    private String categoryName;
    private UUID idCategory;
    private List<ProductImage> images = new ArrayList<>();
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean active = true;
}
