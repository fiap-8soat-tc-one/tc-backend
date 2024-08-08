package com.fiap.tc.core.application.port.in.order;

import com.fiap.tc.adapters.driver.presentation.requests.OrderStatusRequest;

public interface UpdateStatusOrderInputPort {
    void update(OrderStatusRequest orderStatusRequest);
}
