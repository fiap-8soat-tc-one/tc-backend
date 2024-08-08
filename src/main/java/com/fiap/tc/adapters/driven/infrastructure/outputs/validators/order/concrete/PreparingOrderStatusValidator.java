package com.fiap.tc.adapters.driven.infrastructure.outputs.validators.order.concrete;

import com.fiap.tc.core.domain.enums.OrderStatus;

import java.util.List;


public class PreparingOrderStatusValidator extends OrderStatusValidatorTemplate {
    @Override
    public List<OrderStatus> next() {
        return List.of(OrderStatus.READY, OrderStatus.CANCELED);
    }
}
