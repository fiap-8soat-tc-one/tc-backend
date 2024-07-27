package com.fiap.tc.adapter.repository.entity;

import com.fiap.tc.adapter.repository.entity.embeddable.Audit;
import com.fiap.tc.core.domain.model.enums.PaymentStatus;
import com.fiap.tc.core.domain.model.enums.PaymentType;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Column(name = "transaction_return")
    private String transactionReturn;

    @Column(name = "card_number", nullable = false, length = 30)
    private String cardNumber;

    @Column(name = "card_print_name", nullable = false)
    private String cardPrintName;

    @Column(name = "card_document", nullable = false, length = 30)
    private String cardDocument;

    @OneToMany(mappedBy = "payment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy(value = "register_date DESC")
    private List<OrderPaymentHistoricEntity> payment_historic;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false, length = 20)
    private PaymentType paymentType;

    @Embedded
    private Audit audit;

    @Column(name = "cancel_date")
    private LocalDateTime cancelDate;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "pending_date")
    private LocalDateTime pendingDate;

    private BigDecimal total;

}
