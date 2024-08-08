package com.fiap.tc.core.application.ports.out.order;

import com.fiap.tc.core.domain.fixed.OrderStatus;

import java.util.UUID;

public interface UpdateStatusOrderOutputPort {
    void update(UUID idOrder, OrderStatus status);
}
