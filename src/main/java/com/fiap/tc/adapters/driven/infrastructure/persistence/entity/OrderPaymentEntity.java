package com.fiap.tc.adapters.driven.infrastructure.persistence.entity;

import com.fiap.tc.adapters.driven.infrastructure.persistence.entity.embeddable.Audit;
import com.fiap.tc.core.domain.model.enums.PaymentResult;
import com.fiap.tc.core.domain.model.enums.PaymentType;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "order_payment")
public class OrderPaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private UUID uuid;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order", nullable = false)
    private OrderEntity order;

    @Column(name = "transaction_number")
    private String transactionNumber;

    @Column(name = "transaction_message")
    private String transactionMessage;

    @Column(name = "transaction_document", nullable = false, length = 30)
    private String transactionDocument;

    @OneToMany(mappedBy = "payment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy(value = "register_date DESC")
    private List<OrderPaymentHistoricEntity> payment_historic = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "result", nullable = false, length = 20)
    private PaymentResult result;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false, length = 20)
    private PaymentType paymentType;

    private BigDecimal total;

    @Embedded
    private Audit audit;

    @Column(name = "result_success_date")
    private LocalDateTime resultSuccessDate;

    @Column(name = "result_error_date")
    private LocalDateTime resultErrorDate;


}
