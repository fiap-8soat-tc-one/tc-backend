package com.fiap.tc.core.port.in.order;

import com.fiap.tc.core.domain.model.Order;
import com.fiap.tc.core.domain.requests.OrderRequest;
import com.fiap.tc.core.domain.requests.OrderStatusRequest;

public interface UpdateStatusOrderInputPort {
    Order update(OrderStatusRequest orderStatusRequest);
}
