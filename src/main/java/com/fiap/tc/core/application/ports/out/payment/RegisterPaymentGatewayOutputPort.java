package com.fiap.tc.core.application.ports.out.payment;

import com.fiap.tc.core.domain.entities.OrderGatewayPayment;
import com.fiap.tc.adapters.driver.presentation.requests.OrderPaymentRequest;

import java.math.BigDecimal;
import java.util.UUID;

public interface RegisterPaymentGatewayOutputPort {
    OrderGatewayPayment register(OrderPaymentRequest orderPaymentRequest, UUID orderId, BigDecimal total);
}
