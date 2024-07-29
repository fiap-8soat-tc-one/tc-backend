package com.fiap.tc.core.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentResult {
    SUCCESS("payment confirmed", OrderStatus.CONFIRMED),
    ERROR("payment pending", OrderStatus.PENDING);

    private final String description;
    private final OrderStatus orderStatus;

}
