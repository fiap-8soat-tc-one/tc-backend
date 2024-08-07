package com.fiap.tc.application.port.in.payment;

import com.fiap.tc.infrastructure.adapter.web.requests.OrderPaymentRequest;

public interface RegisterPaymentInputPort {
    void register(OrderPaymentRequest orderPaymentRequest);
}
