package com.fiap.tc.core.application.port.out.order;

import com.fiap.tc.core.domain.model.Order;
import com.fiap.tc.adapters.driver.presentation.requests.OrderItemRequest;

import java.util.List;
import java.util.UUID;

public interface RegisterOrderOutputPort {
    Order save(UUID customerId, List<OrderItemRequest> itemsRequest);
}


