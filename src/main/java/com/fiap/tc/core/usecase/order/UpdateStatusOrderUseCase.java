package com.fiap.tc.core.usecase.order;

import com.fiap.tc.core.domain.model.Order;
import com.fiap.tc.core.domain.requests.OrderStatusRequest;
import com.fiap.tc.core.port.in.order.UpdateStatusOrderInputPort;
import com.fiap.tc.core.port.out.order.UpdateStatusOrderOutputPort;
import org.springframework.stereotype.Service;

@Service
public class UpdateStatusOrderUseCase implements UpdateStatusOrderInputPort {

    private final UpdateStatusOrderOutputPort updateStatusOrderOutputPort;

    public UpdateStatusOrderUseCase(UpdateStatusOrderOutputPort updateStatusOrderOutputPort) {
        this.updateStatusOrderOutputPort = updateStatusOrderOutputPort;
    }

    @Override
    public Order update(OrderStatusRequest orderStatusRequest) {
        return updateStatusOrderOutputPort.update(orderStatusRequest.getId(), orderStatusRequest.getStatus());
    }
}
