package com.fiap.tc.core.domain.behavior.order.status.validator;

import com.fiap.tc.core.domain.model.enums.OrderStatus;

import java.util.List;


public class ReceivedOrderStatusValidator extends OrderStatusValidatorTemplate {
    @Override
    public List<OrderStatus> next() {
        return List.of(OrderStatus.CONFIRMED, OrderStatus.PENDING, OrderStatus.CANCELED);
    }
}
