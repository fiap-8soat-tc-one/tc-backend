package com.fiap.tc.core.application.ports.in.payment;

import com.fiap.tc.adapters.driver.presentation.requests.OrderPaymentRequest;

public interface RegisterPaymentInputPort {
    void register(OrderPaymentRequest orderPaymentRequest);
}
