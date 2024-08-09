package com.fiap.tc.core.application.ports.out.payment;

import com.fiap.tc.core.domain.entities.OrderPayment;
import com.fiap.tc.adapters.driver.presentation.requests.OrderPaymentRequest;
import com.fiap.tc.core.domain.enums.PaymentResult;
import com.fiap.tc.core.domain.enums.PaymentType;

import java.math.BigDecimal;

public interface RegisterPaymentOutputPort {

    OrderPayment saveOrUpdate(String transactionNumber, String transactionMessage, String transactionDocument, PaymentResult result, PaymentType type, BigDecimal total);
}
