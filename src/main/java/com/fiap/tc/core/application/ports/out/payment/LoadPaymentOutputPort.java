package com.fiap.tc.core.application.ports.out.payment;

import com.fiap.tc.core.domain.entities.OrderPayment;

import java.util.UUID;

public interface LoadPaymentOutputPort {
    OrderPayment load(UUID orderId);
}

