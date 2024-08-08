package com.fiap.tc.core.port.in.order;

import com.fiap.tc.core.domain.requests.OrderStatusRequest;

public interface UpdateStatusOrderInputPort {
    void update(OrderStatusRequest orderStatusRequest);
}
