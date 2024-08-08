package com.fiap.tc.core.port.in.payment;

import com.fiap.tc.core.domain.requests.OrderPaymentRequest;

public interface RegisterPaymentInputPort {
    void register(OrderPaymentRequest orderPaymentRequest);
}
