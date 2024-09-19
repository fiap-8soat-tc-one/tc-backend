package com.fiap.tc.core.application.usecase.payment;

import com.fiap.tc.core.application.usecase.ports.in.payment.RegisterPaymentInputPort;
import com.fiap.tc.core.application.usecase.ports.out.order.UpdateStatusOrderOutputPort;
import com.fiap.tc.core.application.usecase.ports.out.payment.RegisterPaymentOutputPort;
import com.fiap.tc.core.domain.enums.PaymentStatus;
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
    public void register(String transactionNumber, String transactionMessage, String transactionDocument, PaymentStatus result, PaymentType type, BigDecimal total) {
        var orderPayment = registerPaymentOutputPort.saveOrUpdate(transactionNumber, transactionMessage, transactionDocument, result, type, total);
        updateStatusOrderOutputPort.update(orderPayment.getId(), orderPayment.getStatus().getOrderStatus());
    }
}
