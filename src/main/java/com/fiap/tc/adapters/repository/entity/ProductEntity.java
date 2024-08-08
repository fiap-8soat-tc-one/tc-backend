package com.fiap.tc.adapters.repository.entity;

import com.fiap.tc.adapters.repository.entity.embeddable.Audit;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private UUID uuid;

    private String name;

    private String description;

    private BigDecimal price;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy(value = "register_date DESC")
    private List<ProductImageEntity> images = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category", nullable = false)
    private CategoryEntity category;

    @Embedded
    private Audit audit;

}
