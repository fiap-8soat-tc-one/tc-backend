package com.fiap.tc.adapters.driven.infrastructure.output.validator.order;

import com.fiap.tc.core.domain.model.enums.OrderStatus;

public interface OrderStatusValidator {
    void validate(OrderStatus status);
}
