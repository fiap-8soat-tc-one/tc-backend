package com.fiap.tc.core.application.port.in.order;

import com.fiap.tc.core.domain.requests.OrderRequest;
import com.fiap.tc.core.domain.response.OrderResponse;

public interface RegisterOrderInputPort {
    OrderResponse register(OrderRequest orderRequest);
}
