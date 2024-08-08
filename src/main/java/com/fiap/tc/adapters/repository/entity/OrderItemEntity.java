package com.fiap.tc.adapters.repository.entity;

import com.fiap.tc.adapters.repository.entity.embeddable.Audit;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "item")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product", nullable = false)
    private ProductEntity product;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order", nullable = false)
    private OrderEntity order;

    private Integer quantity;

    @Column(name = "unit_value")
    private BigDecimal unitValue;

    private BigDecimal total;

    @Embedded
    private Audit audit;

}
