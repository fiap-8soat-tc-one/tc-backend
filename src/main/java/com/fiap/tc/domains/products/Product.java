package com.fiap.tc.domains.products;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class Product {
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
    private Set<Category> categories;


}
