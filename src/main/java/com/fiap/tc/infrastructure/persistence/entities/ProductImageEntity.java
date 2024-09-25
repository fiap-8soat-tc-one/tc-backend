package com.fiap.tc.infrastructure.persistence.entities;

import com.fiap.tc.infrastructure.persistence.entities.embeddable.Audit;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;


@Data
@Entity
@Table(name = "product_image")
public class ProductImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private UUID uuid;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product", nullable = false)
    private ProductEntity product;

    private String name;

    private String description;

    private String image;

    @Embedded
    private Audit audit;

}
