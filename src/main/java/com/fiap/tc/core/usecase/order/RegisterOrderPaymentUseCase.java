package com.fiap.tc.core.usecase.order;

import com.fiap.tc.core.domain.model.OrderPayment;
import com.fiap.tc.core.domain.requests.OrderPaymentRequest;
import com.fiap.tc.core.port.out.order.LoadOrderOutputPort;
import com.fiap.tc.core.port.out.order.RegisterOrderPaymentOutputPort;
import com.fiap.tc.core.port.out.payment.RegisterPaymentGatewayOutputPort;
import com.fiap.tc.core.port.out.payment.RegisterPaymentOutputPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegisterOrderPaymentUseCase implements RegisterOrderPaymentOutputPort {

    private final LoadOrderOutputPort loadOrderOutputPort;
    private final RegisterPaymentGatewayOutputPort registerPaymentGatewayOutputPort;
    private final RegisterPaymentOutputPort registerPaymentOutputPort;

    public RegisterOrderPaymentUseCase(LoadOrderOutputPort loadOrderOutputPort,
                                       RegisterPaymentGatewayOutputPort registerPaymentGatewayOutputPort,
                                       RegisterPaymentOutputPort registerPaymentOutputPort) {
        this.loadOrderOutputPort = loadOrderOutputPort;
        this.registerPaymentGatewayOutputPort = registerPaymentGatewayOutputPort;
        this.registerPaymentOutputPort = registerPaymentOutputPort;
    }

    @Override
    public OrderPayment register(OrderPaymentRequest orderPaymentRequest, UUID orderId) {

        var order = loadOrderOutputPort.load(orderId);
        var orderGatewayPayment = registerPaymentGatewayOutputPort.register(orderPaymentRequest, orderId, order.getTotal());
        return registerPaymentOutputPort.saveOrUpdate(orderGatewayPayment, orderId);

    }
}
