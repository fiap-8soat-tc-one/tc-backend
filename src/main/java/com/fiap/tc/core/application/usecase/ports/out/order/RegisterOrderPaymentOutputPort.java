package com.fiap.tc.core.application.usecase.ports.out.order;

import com.fiap.tc.core.domain.entities.OrderPayment;
import com.fiap.tc.adapters.driver.presentation.requests.OrderPaymentRequest;

import java.util.UUID;

public interface RegisterOrderPaymentOutputPort {
    OrderPayment register(OrderPaymentRequest orderPaymentRequest, UUID orderId);
}


