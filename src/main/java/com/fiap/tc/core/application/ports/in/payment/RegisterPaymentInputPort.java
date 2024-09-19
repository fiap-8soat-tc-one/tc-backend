package com.fiap.tc.core.application.ports.in.payment;

import com.fiap.tc.core.domain.enums.PaymentStatus;
import com.fiap.tc.core.domain.enums.PaymentType;

import java.math.BigDecimal;

public interface RegisterPaymentInputPort {
    void register(String transactionNumber, String transactionMessage, String transactionDocument, PaymentStatus result, PaymentType type, BigDecimal total);
}
