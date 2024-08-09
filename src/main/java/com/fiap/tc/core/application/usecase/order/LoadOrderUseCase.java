package com.fiap.tc.core.application.usecase.order;

import com.fiap.tc.core.application.ports.in.order.LoadOrderInputPort;
import com.fiap.tc.core.application.ports.out.order.LoadOrderOutputPort;
import com.fiap.tc.core.domain.entities.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class LoadOrderUseCase implements LoadOrderInputPort {
    private final LoadOrderOutputPort loadOrderOutputPort;

    public LoadOrderUseCase(LoadOrderOutputPort loadOrderOutputPort) {
        this.loadOrderOutputPort = loadOrderOutputPort;
    }

    @Override
    public Order load(UUID uuid) {
        return loadOrderOutputPort.load(uuid);
    }
}







