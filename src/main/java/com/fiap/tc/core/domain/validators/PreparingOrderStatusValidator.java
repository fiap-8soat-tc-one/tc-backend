package com.fiap.tc.core.domain.validators;

import com.fiap.tc.core.domain.enums.OrderStatus;

import java.util.List;


public class PreparingOrderStatusValidator extends OrderStatusValidatorTemplate {
    @Override
    public List<OrderStatus> next() {
        return List.of(OrderStatus.READY);
    }
}
