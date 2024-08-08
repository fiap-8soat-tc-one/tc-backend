package com.fiap.tc.adapter.repository.entity;

import com.fiap.tc.adapter.repository.entity.embeddable.Audit;
import com.fiap.tc.core.domain.model.enums.OrderStatus;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "order_request")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private UUID uuid;

    @Column(name = "order_number")
    private String orderNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer")
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy(value = "register_date DESC")
    private List<OrderHistoricEntity> orderHistoric = new ArrayList<>();

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy(value = "register_date DESC")
    private List<OrderItemEntity> items;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @Embedded
    private Audit audit;

    private BigDecimal total;

}
