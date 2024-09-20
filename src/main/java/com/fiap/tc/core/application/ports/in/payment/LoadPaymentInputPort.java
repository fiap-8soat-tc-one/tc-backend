package com.fiap.tc.core.application.ports.in.payment;

import com.fiap.tc.core.domain.entities.OrderPayment;

import java.util.UUID;

public interface LoadPaymentInputPort {
    OrderPayment load(UUID orderId);
}
