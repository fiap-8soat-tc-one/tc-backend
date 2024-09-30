package com.fiap.tc.infrastructure.gateways.validators.order;

import com.fiap.tc.domain.enums.OrderStatus;

public interface OrderStatusValidator {
    void validate(OrderStatus status);
}
