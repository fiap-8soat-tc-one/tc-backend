package com.fiap.tc.core.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentResult {
    SUCCESS("order payed", OrderStatus.PREPARING),
    ERROR("order pending", OrderStatus.PENDING);

    private final String description;
    private final OrderStatus orderStatus;

}
