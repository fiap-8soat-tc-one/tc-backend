package com.fiap.tc.application.gateways;

import com.fiap.tc.domain.entities.OrderPayment;
import com.fiap.tc.domain.enums.PaymentStatus;
import com.fiap.tc.domain.enums.PaymentType;

import java.math.BigDecimal;
import java.util.UUID;

public interface PaymentGatewaySpec {

    OrderPayment load(UUID idOrder);

    OrderPayment register(String transactionNumber, String transactionMessage, String transactionDocument,
                          PaymentStatus status, PaymentType type, BigDecimal total);

}
