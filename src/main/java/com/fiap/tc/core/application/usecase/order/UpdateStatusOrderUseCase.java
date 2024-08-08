package com.fiap.tc.core.application.usecase.order;

import com.fiap.tc.core.application.port.in.order.UpdateStatusOrderInputPort;
import com.fiap.tc.core.domain.requests.OrderStatusRequest;
import com.fiap.tc.core.application.port.out.order.UpdateStatusOrderOutputPort;
import org.springframework.stereotype.Service;

@Service
public class UpdateStatusOrderUseCase implements UpdateStatusOrderInputPort {

    private final UpdateStatusOrderOutputPort updateStatusOrderOutputPort;

    public UpdateStatusOrderUseCase(UpdateStatusOrderOutputPort updateStatusOrderOutputPort) {
        this.updateStatusOrderOutputPort = updateStatusOrderOutputPort;
    }

    @Override
    public void update(OrderStatusRequest orderStatusRequest) {
        updateStatusOrderOutputPort.update(orderStatusRequest.getId(), orderStatusRequest.getStatus());
    }
}
