package com.fiap.tc.infrastructure.persistence.builders;

import com.fiap.tc.infrastructure.persistence.entities.OrderEntity;
import com.fiap.tc.infrastructure.persistence.entities.OrderHistoricEntity;
import com.fiap.tc.domain.enums.OrderStatus;

import java.time.LocalDateTime;

public class OrderHistoricBuilder {
    private OrderHistoricBuilder() {
    }

    public static OrderHistoricEntity create(OrderEntity orderEntity, OrderStatus status) {
        return OrderHistoricEntity.builder()
                .order(orderEntity)
                .status(status)
                .registerDate(LocalDateTime.now())
                .build();

    }
}
