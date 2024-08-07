package com.fiap.tc.core.domain.validators;

import com.fiap.tc.core.domain.enums.OrderStatus;

public interface OrderStatusValidator {
    void validate(OrderStatus status);
}
