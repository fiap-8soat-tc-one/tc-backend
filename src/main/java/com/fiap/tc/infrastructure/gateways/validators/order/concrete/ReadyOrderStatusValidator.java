package com.fiap.tc.infrastructure.gateways.validators.order.concrete;

import com.fiap.tc.domain.enums.OrderStatus;

import java.util.List;


public class ReadyOrderStatusValidator extends OrderStatusValidatorTemplate {
    @Override
    public List<OrderStatus> next() {
        return List.of(OrderStatus.FINISHED, OrderStatus.CANCELED);
    }
}
