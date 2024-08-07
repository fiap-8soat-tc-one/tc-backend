package com.fiap.tc.application.port.out.order;

import com.fiap.tc.core.domain.enums.OrderStatus;

import java.util.UUID;

public interface UpdateStatusOrderOutputPort {
    void update(UUID idOrder, OrderStatus status);
}
