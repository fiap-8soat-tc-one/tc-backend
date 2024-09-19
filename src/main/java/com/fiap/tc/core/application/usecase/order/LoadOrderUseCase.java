package com.fiap.tc.core.application.usecase.order;

import com.fiap.tc.core.application.usecase.ports.in.order.LoadOrderInputPort;
import com.fiap.tc.core.application.usecase.ports.out.order.LoadOrderOutputPort;
import com.fiap.tc.core.application.usecase.ports.out.payment.PaymentLinkOutputPort;
import com.fiap.tc.core.domain.entities.Order;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoadOrderUseCase implements LoadOrderInputPort {

    private final LoadOrderOutputPort loadOrderOutputPort;
    private final PaymentLinkOutputPort paymentLinkOutputPort;

    public LoadOrderUseCase(LoadOrderOutputPort loadOrderOutputPort, PaymentLinkOutputPort paymentLinkOutputPort) {
        this.loadOrderOutputPort = loadOrderOutputPort;
        this.paymentLinkOutputPort = paymentLinkOutputPort;
    }

    @Override
    public Order load(UUID uuid) {
        var order = loadOrderOutputPort.load(uuid);
        paymentLinkOutputPort.generate(order).ifPresent(order::setPaymentLink);
        return order;
    }
}







