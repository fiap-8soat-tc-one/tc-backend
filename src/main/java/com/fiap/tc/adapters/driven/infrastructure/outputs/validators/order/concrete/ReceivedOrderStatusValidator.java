package com.fiap.tc.adapters.driven.infrastructure.outputs.validators.order.concrete;

import com.fiap.tc.core.domain.enums.OrderStatus;

import java.util.List;


public class ReceivedOrderStatusValidator extends OrderStatusValidatorTemplate {
    @Override
    public List<OrderStatus> next() {
        return List.of(OrderStatus.PREPARING, OrderStatus.PENDING, OrderStatus.CANCELED);
    }
}
