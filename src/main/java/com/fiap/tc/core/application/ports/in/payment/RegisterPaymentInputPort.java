package com.fiap.tc.core.application.ports.in.payment;

import com.fiap.tc.adapters.driver.presentation.requests.OrderPaymentRequest;
import com.fiap.tc.core.domain.enums.PaymentResult;
import com.fiap.tc.core.domain.enums.PaymentType;

import java.math.BigDecimal;

public interface RegisterPaymentInputPort {
    void register(String transactionNumber, String transactionMessage, String transactionDocument, PaymentResult result, PaymentType type, BigDecimal total);
}
