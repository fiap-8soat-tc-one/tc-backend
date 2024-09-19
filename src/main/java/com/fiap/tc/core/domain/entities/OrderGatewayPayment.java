package com.fiap.tc.core.domain.entities;

import com.fiap.tc.adapters.driver.presentation.requests.OrderPaymentRequest;
import com.fiap.tc.core.domain.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderGatewayPayment extends OrderPaymentRequest {
    private String transactionNumber;
    private String transactionReturn;
    private PaymentStatus status;
    private BigDecimal total;
    private LocalDateTime pendingDate;
    private LocalDateTime paymentDate;
}
