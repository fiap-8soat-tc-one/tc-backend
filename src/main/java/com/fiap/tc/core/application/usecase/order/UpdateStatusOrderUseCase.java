package com.fiap.tc.core.application.usecase.order;

import com.fiap.tc.core.application.ports.in.order.UpdateStatusOrderInputPort;
import com.fiap.tc.core.application.ports.out.order.UpdateStatusOrderOutputPort;
import com.fiap.tc.core.domain.enums.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateStatusOrderUseCase implements UpdateStatusOrderInputPort {

    private final UpdateStatusOrderOutputPort updateStatusOrderOutputPort;

    public UpdateStatusOrderUseCase(UpdateStatusOrderOutputPort updateStatusOrderOutputPort) {
        this.updateStatusOrderOutputPort = updateStatusOrderOutputPort;
    }

    @Override
    public void update(UUID id, OrderStatus status) {
        updateStatusOrderOutputPort.update(id, status);
    }
}
