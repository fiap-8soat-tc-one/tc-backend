package com.fiap.tc.core.application.port.in.order;

import com.fiap.tc.adapters.driver.presentation.requests.OrderRequest;
import com.fiap.tc.adapters.driver.presentation.response.OrderResponse;

public interface RegisterOrderInputPort {
    OrderResponse register(OrderRequest orderRequest);
}
