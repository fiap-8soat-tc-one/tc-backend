package com.fiap.tc.core.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {
    RECEIVED("order received"),
    CONFIRMED("confirmed order with payment"),
    PENDING("pending payment order"),
    PREPARING("order being prepared"),
    READY("order ready"),
    FINISHED("order finished"),
    CANCELED("order canceled");

    private final String description;
}
