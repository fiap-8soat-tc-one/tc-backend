package com.fiap.tc.adapters.driven.infrastructure.outputs.validators.order;

import com.fiap.tc.core.domain.enums.OrderStatus;

public interface OrderStatusValidator {
    void validate(OrderStatus status);
}
