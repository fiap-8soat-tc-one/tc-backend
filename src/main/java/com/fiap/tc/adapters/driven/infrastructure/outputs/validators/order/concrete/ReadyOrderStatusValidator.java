package com.fiap.tc.adapters.driven.infrastructure.outputs.validators.order.concrete;

import com.fiap.tc.core.domain.enums.OrderStatus;

import java.util.List;


public class ReadyOrderStatusValidator extends OrderStatusValidatorTemplate {
    @Override
    public List<OrderStatus> next() {
        return List.of(OrderStatus.FINISHED, OrderStatus.CANCELED);
    }
}
