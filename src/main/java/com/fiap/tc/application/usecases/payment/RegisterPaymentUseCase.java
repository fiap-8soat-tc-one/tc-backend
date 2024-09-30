package com.fiap.tc.application.usecases.payment;

import com.fiap.tc.application.gateways.OrderGatewaySpec;
import com.fiap.tc.application.gateways.PaymentGatewaySpec;
import com.fiap.tc.domain.enums.PaymentStatus;
import com.fiap.tc.domain.enums.PaymentType;
import com.fiap.tc.infrastructure.gateways.OrderGateway;
import com.fiap.tc.infrastructure.gateways.PaymentGateway;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RegisterPaymentUseCase {
    private final PaymentGatewaySpec paymentGateway;
    private final OrderGatewaySpec orderGateway;

    public RegisterPaymentUseCase(PaymentGateway paymentGateway, OrderGateway orderGateway) {
        this.paymentGateway = paymentGateway;
        this.orderGateway = orderGateway;
    }

    public void register(String transactionNumber, String transactionMessage, String transactionDocument,
                         PaymentStatus result, PaymentType type, BigDecimal total) {
        var orderPayment = paymentGateway.register(transactionNumber, transactionMessage, transactionDocument,
                result, type, total);
        orderGateway.updateStatus(orderPayment.getId(), orderPayment.getStatus().getOrderStatus());
    }
}
