package com.fiap.tc.core.domain.model.enums;

import com.fiap.tc.core.domain.behavior.order.status.validator.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {
    RECEIVED("order received", new ReceivedOrderStatusValidator()),
    PENDING("pending payment order", new PendingOrderStatusValidator()),
    PREPARING("order being prepared", new PreparingOrderStatusValidator()),
    READY("order ready", new ReadyOrderStatusValidator()),
    FINISHED("order finished", new FinishedOrderStatusValidator()),
    CANCELED("order canceled", new CanceledOrderStatusValidator());

    private final String description;
    private final OrderStatusValidator validator;
}
