package com.fiap.tc.adapters.driven.infrastructure.persistence.entity;

import com.fiap.tc.adapters.driven.infrastructure.persistence.entity.embeddable.Audit;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private UUID uuid;

    private String name;

    private String description;

    @Embedded
    private Audit audit;
}
