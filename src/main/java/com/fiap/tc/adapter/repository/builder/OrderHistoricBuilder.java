package com.fiap.tc.adapter.repository.builder;

import com.fiap.tc.adapter.repository.entity.OrderEntity;
import com.fiap.tc.adapter.repository.entity.OrderHistoricEntity;
import com.fiap.tc.core.domain.model.enums.OrderStatus;

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
