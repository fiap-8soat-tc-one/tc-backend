package com.fiap.tc.core.domain.entities;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductImage {
    private UUID id;
    private String name;
    private String description;
    private String image;
}
