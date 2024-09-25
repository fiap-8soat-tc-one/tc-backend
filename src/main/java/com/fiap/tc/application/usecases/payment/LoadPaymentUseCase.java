package com.fiap.tc.application.usecases.payment;

import com.fiap.tc.application.gateways.PaymentGatewaySpec;
import com.fiap.tc.domain.entities.OrderPayment;
import com.fiap.tc.infrastructure.gateways.PaymentGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoadPaymentUseCase {

    private final PaymentGatewaySpec paymentGateway;

    public LoadPaymentUseCase(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public OrderPayment load(UUID orderId) {
        return paymentGateway.load(orderId);
    }
}


