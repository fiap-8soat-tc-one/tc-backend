package com.fiap.tc.adapters.driven.infrastructure.persistence.builder;

import com.fiap.tc.adapters.driven.infrastructure.persistence.entity.OrderPaymentEntity;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entity.OrderPaymentHistoricEntity;
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
