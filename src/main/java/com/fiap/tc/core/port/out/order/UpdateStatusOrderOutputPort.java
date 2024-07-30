package com.fiap.tc.core.port.out.order;

import com.fiap.tc.core.domain.model.Order;
import com.fiap.tc.core.domain.model.enums.OrderStatus;

import java.util.UUID;

public interface UpdateStatusOrderOutputPort {
    Order update(UUID idOrder, OrderStatus status);
}
