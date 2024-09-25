package com.fiap.tc.infrastructure.gateways.validators.order.concrete;

import com.fiap.tc.domain.enums.OrderStatus;

import java.util.List;


public class PendingOrderStatusValidator extends OrderStatusValidatorTemplate {
    @Override
    public List<OrderStatus> next() {
        return List.of(OrderStatus.PENDING, OrderStatus.PREPARING, OrderStatus.CANCELED);
    }
}
