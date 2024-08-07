package com.fiap.tc.infrastructure.adapter.persistence.entity;

import com.fiap.tc.core.domain.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "order_request_historic")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistoricEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order")
    private OrderEntity order;

    @Column(name = "register_date", nullable = false)
    private LocalDateTime registerDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;


}
