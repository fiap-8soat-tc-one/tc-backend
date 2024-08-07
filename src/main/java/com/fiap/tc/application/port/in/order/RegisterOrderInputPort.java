package com.fiap.tc.core.port.in.order;

import com.fiap.tc.infrastructure.adapter.web.requests.OrderRequest;
import com.fiap.tc.infrastructure.adapter.web.response.OrderResponse;

public interface RegisterOrderInputPort {
    OrderResponse register(OrderRequest orderRequest);
}
