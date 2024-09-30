package com.fiap.tc.application.usecases.order;

import com.fiap.tc.application.gateways.OrderGatewaySpec;
import com.fiap.tc.domain.enums.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateStatusOrderUseCase {

    private final OrderGatewaySpec orderGateway;

    public UpdateStatusOrderUseCase(OrderGatewaySpec orderGateway) {
        this.orderGateway = orderGateway;
    }

    public void update(UUID id, OrderStatus status) {
        orderGateway.updateStatus(id, status);
    }
}
