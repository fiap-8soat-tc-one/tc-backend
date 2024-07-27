package com.fiap.tc.core.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentStatus {
    PAID("payment confirmed", OrderStatus.CONFIRMED),
    PENDING("payment pending", OrderStatus.PENDING);

    private final String description;
    private final OrderStatus orderStatus;
}
