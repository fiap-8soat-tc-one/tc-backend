package com.fiap.tc.core.port.in.order;

import com.fiap.tc.infrastructure.adapter.web.requests.OrderStatusRequest;

public interface UpdateStatusOrderInputPort {
    void update(OrderStatusRequest orderStatusRequest);
}
