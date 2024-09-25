package com.fiap.tc.infrastructure.persistence.entities;

import com.fiap.tc.infrastructure.persistence.entities.embeddable.Audit;
import com.fiap.tc.domain.enums.PaymentStatus;
import com.fiap.tc.domain.enums.PaymentType;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "order_payment")
public class OrderPaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
    private List<OrderPaymentHistoricEntity> paymentHistoric = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private PaymentStatus status;

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
