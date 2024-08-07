package com.fiap.tc.application.port.in.order;

import com.fiap.tc.infrastructure.adapter.web.requests.OrderRequest;
import com.fiap.tc.infrastructure.adapter.web.responses.OrderResponse;

public interface RegisterOrderInputPort {
    OrderResponse register(OrderRequest orderRequest);
}
