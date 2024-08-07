package com.fiap.tc.core.domain.behavior.order.status.validator;

import com.fiap.tc.core.domain.enums.OrderStatus;

public interface OrderStatusValidator {
    void validate(OrderStatus status);
}
