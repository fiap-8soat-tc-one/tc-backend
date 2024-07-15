package com.fiap.tc.domains.products;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private UUID uuid;
    private String name;
    private String description;
    @Column(name = "fl_active", columnDefinition = "boolean default true", nullable = false)
    private boolean active;
}
