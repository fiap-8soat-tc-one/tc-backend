package com.fiap.tc.adapter.service.output;

import com.fiap.tc.common.config.AppConfiguration;
import com.fiap.tc.core.domain.model.OrderGatewayPayment;
import com.fiap.tc.core.domain.model.enums.PaymentStatus;
import com.fiap.tc.core.domain.requests.OrderPaymentRequest;
import com.fiap.tc.core.port.out.payment.RegisterPaymentGatewayOutputPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RegisterPaymentGatewayOutputAdapterMock implements RegisterPaymentGatewayOutputPort {

    private final AppConfiguration appConfiguration;

    public RegisterPaymentGatewayOutputAdapterMock(AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
    }

    @Override
    public OrderGatewayPayment register(OrderPaymentRequest request, UUID orderId, BigDecimal total) {
        return appConfiguration.getOrderPaymentMockSuccess() ? successResponse(request, orderId, total) : errorResponse(request, orderId, total);
    }

    private OrderGatewayPayment successResponse(OrderPaymentRequest request, UUID orderId, BigDecimal total) {
        var payment = new OrderGatewayPayment();

        payment.setStatus(PaymentStatus.PAID);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setTotal(total);
        payment.setTransactionNumber(orderId.toString());
        payment.setTransactionReturn("transaction confirmed");
        payment.setCardNumber(request.getCardNumber());
        payment.setCardPrintName(request.getCardPrintName());
        payment.setCardDocument(request.getCardDocument());
        payment.setPaymentType(request.getPaymentType());

        return payment;
    }

    private OrderGatewayPayment errorResponse(OrderPaymentRequest request, UUID orderId, BigDecimal total) {
        var payment = new OrderGatewayPayment();

        payment.setStatus(PaymentStatus.PENDING);
        payment.setPendingDate(LocalDateTime.now());
        payment.setTotal(total);
        payment.setTransactionNumber(orderId.toString());
        payment.setTransactionReturn("transaction error");
        payment.setCardNumber(request.getCardNumber());
        payment.setCardPrintName(request.getCardPrintName());
        payment.setCardDocument(request.getCardDocument());
        payment.setPaymentType(request.getPaymentType());

        return payment;
    }
}
