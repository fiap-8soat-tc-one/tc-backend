package com.fiap.tc.application.usecase.payment;

import com.fiap.tc.application.port.in.payment.RegisterPaymentInputPort;
import com.fiap.tc.application.port.out.order.UpdateStatusOrderOutputPort;
import com.fiap.tc.application.port.out.payment.RegisterPaymentOutputPort;
import com.fiap.tc.infrastructure.adapter.web.requests.OrderPaymentRequest;
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
