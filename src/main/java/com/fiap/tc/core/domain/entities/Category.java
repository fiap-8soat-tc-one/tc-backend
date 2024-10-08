package com.fiap.tc.core.domain.entities;

import lombok.Data;

import java.util.UUID;

@Data
public class Category {
    private UUID id;
    private String name;
    private String description;
}
