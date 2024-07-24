package com.fiap.tc.adapter.repository.entity;

import com.fiap.tc.adapter.repository.entity.embeddable.Audit;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "item")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="id_product", nullable = false)
    private ProductEntity product;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="id_order", nullable = false)
    private OrderEntity order;

    private Integer quantity;

    @Column(name = "unit_value")
    private BigDecimal unitValue;

    private BigDecimal total;

    @Embedded
    private Audit audit;

    @Column(name = "cancel_date")
    private LocalDateTime cancelDate;

}
