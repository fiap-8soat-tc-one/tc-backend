package com.fiap.tc.adapter.repository.entity;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String name;
    private String description;
    @Column(name = "fl_active", columnDefinition = "boolean default true")
    private Boolean active;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "product_category",
            joinColumns = { @JoinColumn(name = "id_product") },
            inverseJoinColumns = { @JoinColumn(name = "id_category") }
    )
    private Set<CategoryEntity> categories;


}
