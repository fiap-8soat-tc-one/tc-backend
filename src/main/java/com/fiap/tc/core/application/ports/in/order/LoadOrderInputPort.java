package com.fiap.tc.core.application.ports.in.order;

import com.fiap.tc.core.domain.entities.Order;

import java.util.UUID;

public interface LoadOrderInputPort {
    Order load(UUID uuid);
}
