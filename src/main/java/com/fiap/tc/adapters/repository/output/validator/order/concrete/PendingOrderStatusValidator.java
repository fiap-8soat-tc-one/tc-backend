package com.fiap.tc.adapters.repository.output.validator.order.concrete;

import com.fiap.tc.core.domain.model.enums.OrderStatus;

import java.util.List;


public class PendingOrderStatusValidator extends OrderStatusValidatorTemplate {
    @Override
    public List<OrderStatus> next() {
        return List.of(OrderStatus.PENDING, OrderStatus.PREPARING, OrderStatus.CANCELED);
    }
}
