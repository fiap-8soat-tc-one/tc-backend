package com.fiap.tc.core.application.usecase.payment;

import com.fiap.tc.core.application.ports.in.payment.RegisterPaymentInputPort;
import com.fiap.tc.adapters.driver.presentation.requests.OrderPaymentRequest;
import com.fiap.tc.core.application.ports.out.order.UpdateStatusOrderOutputPort;
import com.fiap.tc.core.application.ports.out.payment.RegisterPaymentOutputPort;
import com.fiap.tc.core.domain.enums.PaymentResult;
import com.fiap.tc.core.domain.enums.PaymentType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RegisterPaymentUseCase implements RegisterPaymentInputPort {
    private final RegisterPaymentOutputPort registerPaymentOutputPort;
    private final UpdateStatusOrderOutputPort updateStatusOrderOutputPort;

    public RegisterPaymentUseCase(RegisterPaymentOutputPort registerPaymentOutputPort,
                                  UpdateStatusOrderOutputPort updateStatusOrderOutputPort) {
        this.registerPaymentOutputPort = registerPaymentOutputPort;
        this.updateStatusOrderOutputPort = updateStatusOrderOutputPort;
    }

    @Override
    public void register(String transactionNumber, String transactionMessage, String transactionDocument, PaymentResult result, PaymentType type, BigDecimal total) {
        var orderPayment = registerPaymentOutputPort.saveOrUpdate(transactionNumber, transactionMessage, transactionDocument, result, type, total);
        updateStatusOrderOutputPort.update(orderPayment.getIdOrder(), orderPayment.getResult().getOrderStatus());
    }
}
