package com.fiap.tc.core.usecase.order;

import com.fiap.tc.core.domain.model.Order;
import com.fiap.tc.core.domain.requests.OrderRequest;
import com.fiap.tc.core.port.in.order.RegisterOrderInputPort;
import com.fiap.tc.core.port.out.order.RegisterOrderOutputPort;
import com.fiap.tc.core.port.out.order.RegisterOrderPaymentOutputPort;
import com.fiap.tc.core.port.out.order.UpdateStatusOrderOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegisterOrderUseCase implements RegisterOrderInputPort {
    private final RegisterOrderOutputPort registerOrderOutputPort;
    private final RegisterOrderPaymentOutputPort registerOrderPaymentOutputPort;
    private final UpdateStatusOrderOutputPort updateStatusOrderOutputPort;

    public RegisterOrderUseCase(RegisterOrderOutputPort registerOrderOutputPort,
                                RegisterOrderPaymentOutputPort registerOrderPaymentOutputPort,
                                UpdateStatusOrderOutputPort updateStatusOrderOutputPort) {
        this.registerOrderOutputPort = registerOrderOutputPort;
        this.registerOrderPaymentOutputPort = registerOrderPaymentOutputPort;
        this.updateStatusOrderOutputPort = updateStatusOrderOutputPort;
    }

    @Override
    public Order register(OrderRequest orderRequest) {
        var order = registerOrderOutputPort.save(orderRequest.getIdCustomer(), orderRequest.getOrderPaymentRequest(),
                orderRequest.getOrderItems());
        var orderPayment = registerOrderPaymentOutputPort.register(orderRequest.getOrderPaymentRequest(), order.getId());

        return updateStatusOrderOutputPort.update(order.getId(), orderPayment.getStatus().getOrderStatus());
    }
}
