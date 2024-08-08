package com.fiap.tc.adapters.driven.infrastructure.persistence.entities;

import com.fiap.tc.core.domain.enums.PaymentResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_payment_historic")
public class OrderPaymentHistoricEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order_payment", nullable = false)
    private OrderPaymentEntity payment;

    @Column(name = "register_date", nullable = false)
    private LocalDateTime registerDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "result", nullable = false, length = 20)
    private PaymentResult result;

}
