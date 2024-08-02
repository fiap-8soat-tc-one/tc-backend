package com.fiap.tc.core.port.out.order;

import com.fiap.tc.core.domain.model.enums.OrderStatus;

import java.util.UUID;

public interface UpdateStatusOrderOutputPort {
    void update(UUID idOrder, OrderStatus status);
}
