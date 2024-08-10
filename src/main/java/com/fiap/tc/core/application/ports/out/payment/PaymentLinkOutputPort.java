package com.fiap.tc.core.application.ports.out.payment;

import com.fiap.tc.core.domain.entities.Order;

import java.util.Optional;

public interface PaymentLinkOutputPort {
    Optional<String> generate(Order order);
}
