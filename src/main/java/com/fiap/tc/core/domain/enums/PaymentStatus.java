package com.fiap.tc.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentStatus {
    APPROVED("order payment approved", OrderStatus.PREPARING),
    REFUSED("payment refused order pending", OrderStatus.PENDING),
    ERROR("order payment error", OrderStatus.PENDING);

    private final String description;
    private final OrderStatus orderStatus;

}
