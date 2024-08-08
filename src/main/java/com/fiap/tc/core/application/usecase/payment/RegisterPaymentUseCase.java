package com.fiap.tc.core.application.usecase.payment;

import com.fiap.tc.core.application.ports.in.payment.RegisterPaymentInputPort;
import com.fiap.tc.adapters.driver.presentation.requests.OrderPaymentRequest;
import com.fiap.tc.core.application.ports.out.order.UpdateStatusOrderOutputPort;
import com.fiap.tc.core.application.ports.out.payment.RegisterPaymentOutputPort;
import org.springframework.stereotype.Service;

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
    public void register(OrderPaymentRequest orderPaymentRequest) {
        var orderPayment = registerPaymentOutputPort.saveOrUpdate(orderPaymentRequest);
        updateStatusOrderOutputPort.update(orderPayment.getIdOrder(), orderPayment.getResult().getOrderStatus());
    }
}
