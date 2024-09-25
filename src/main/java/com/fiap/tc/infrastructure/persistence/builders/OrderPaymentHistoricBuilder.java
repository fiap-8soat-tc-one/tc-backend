package com.fiap.tc.infrastructure.persistence.builders;

import com.fiap.tc.infrastructure.persistence.entities.OrderPaymentEntity;
import com.fiap.tc.infrastructure.persistence.entities.OrderPaymentHistoricEntity;
import com.fiap.tc.domain.enums.PaymentStatus;

import java.time.LocalDateTime;

public class OrderPaymentHistoricBuilder {
    private OrderPaymentHistoricBuilder() {
    }

    public static OrderPaymentHistoricEntity create(OrderPaymentEntity orderPaymentEntity, PaymentStatus result) {
        return OrderPaymentHistoricEntity.builder()
                .payment(orderPaymentEntity)
                .status(result)
                .registerDate(LocalDateTime.now())
                .transactionMessage(orderPaymentEntity.getTransactionMessage())
                .build();

    }
}
