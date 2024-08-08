package com.fiap.tc.adapters.driven.infrastructure.persistence.builders;

import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.OrderPaymentEntity;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.OrderPaymentHistoricEntity;
import com.fiap.tc.core.domain.model.enums.PaymentResult;

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
