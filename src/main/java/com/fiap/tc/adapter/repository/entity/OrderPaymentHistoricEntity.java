package com.fiap.tc.adapter.repository.entity;

import com.fiap.tc.core.domain.model.enums.PaymentStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "order_payment_historic")
public class OrderPaymentHistoricEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_order_payment", nullable = false)
    private OrderPaymentEntity payment;

    @Column(name = "register_date", nullable = false)
    private LocalDateTime registerDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private PaymentStatus status;

}
