package com.fiap.tc.core.port.out.order;

import com.fiap.tc.core.domain.model.Order;
import com.fiap.tc.core.domain.requests.OrderItemRequest;
import com.fiap.tc.core.domain.requests.OrderPaymentRequest;

import java.util.List;
import java.util.UUID;

public interface RegisterOrderOutputPort {
    Order save(UUID customerId, OrderPaymentRequest orderPaymentRequest, List<OrderItemRequest> itemsRequest);
}


