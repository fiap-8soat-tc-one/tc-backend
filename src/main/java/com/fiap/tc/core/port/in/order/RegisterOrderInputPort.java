package com.fiap.tc.core.port.in.order;

import com.fiap.tc.core.domain.model.Order;
import com.fiap.tc.core.domain.requests.OrderRequest;

public interface RegisterOrderInputPort {
    Order register(OrderRequest orderRequest);
}
