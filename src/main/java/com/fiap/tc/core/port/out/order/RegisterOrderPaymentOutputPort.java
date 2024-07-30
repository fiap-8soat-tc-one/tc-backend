package com.fiap.tc.core.port.out.order;

import com.fiap.tc.core.domain.model.OrderPayment;
import com.fiap.tc.core.domain.requests.OrderPaymentRequest;

import java.util.UUID;

public interface RegisterOrderPaymentOutputPort {
    OrderPayment register(OrderPaymentRequest orderPaymentRequest, UUID orderId);
}


