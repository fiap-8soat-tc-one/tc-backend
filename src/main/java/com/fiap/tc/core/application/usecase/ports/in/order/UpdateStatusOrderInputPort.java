package com.fiap.tc.core.application.usecase.ports.in.order;

import com.fiap.tc.core.domain.enums.OrderStatus;

import java.util.UUID;

public interface UpdateStatusOrderInputPort {
    void update(UUID id, OrderStatus status);
}
