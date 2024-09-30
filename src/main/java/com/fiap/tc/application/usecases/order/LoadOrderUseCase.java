package com.fiap.tc.application.usecases.order;

import com.fiap.tc.application.gateways.OrderGatewaySpec;
import com.fiap.tc.application.gateways.PaymentLinkGatewaySpec;
import com.fiap.tc.domain.entities.Order;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoadOrderUseCase {

    private final OrderGatewaySpec orderGateway;
    private final PaymentLinkGatewaySpec paymentLinkGateway;

    public LoadOrderUseCase(OrderGatewaySpec orderGateway, PaymentLinkGatewaySpec paymentLinkGateway) {
        this.orderGateway = orderGateway;
        this.paymentLinkGateway = paymentLinkGateway;
    }

    public Order load(UUID uuid) {
        var order = orderGateway.load(uuid);
        paymentLinkGateway.generate(order).ifPresent(order::setPaymentLink);
        return order;
    }
}







