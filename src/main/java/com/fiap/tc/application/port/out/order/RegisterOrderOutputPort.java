package com.fiap.tc.application.port.out.order;

import com.fiap.tc.core.domain.model.Order;
import com.fiap.tc.infrastructure.adapter.web.requests.OrderItemRequest;

import java.util.List;
import java.util.UUID;

public interface RegisterOrderOutputPort {
    Order save(UUID customerId, List<OrderItemRequest> itemsRequest);
}


