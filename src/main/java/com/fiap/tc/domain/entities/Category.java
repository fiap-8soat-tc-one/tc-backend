package com.fiap.tc.domain.entities;

import lombok.Data;

import java.util.UUID;

@Data
public class Category {
    private UUID id;
    private String name;
    private String description;
}
