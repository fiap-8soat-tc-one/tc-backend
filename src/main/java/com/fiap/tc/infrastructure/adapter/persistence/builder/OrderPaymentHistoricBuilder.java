package com.fiap.tc.infrastructure.adapter.persistence.builder;

import com.fiap.tc.infrastructure.adapter.persistence.entity.OrderPaymentEntity;
import com.fiap.tc.infrastructure.adapter.persistence.entity.OrderPaymentHistoricEntity;
import com.fiap.tc.core.domain.enums.PaymentResult;

import java.time.LocalDateTime;

public class OrderPaymentHistoricBuilder {
    private OrderPaymentHistoricBuilder() {
    }

    public static OrderPaymentHistoricEntity create(OrderPaymentEntity orderPaymentEntity, PaymentResult result) {
        return OrderPaymentHistoricEntity.builder()
                .payment(orderPaymentEntity)
                .result(result)
                .registerDate(LocalDateTime.now())
                .build();

    }
}
