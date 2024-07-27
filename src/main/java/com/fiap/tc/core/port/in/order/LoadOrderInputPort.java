package com.fiap.tc.core.port.in.order;

import com.fiap.tc.core.domain.model.Order;

import java.util.UUID;

public interface LoadOrderInputPort {
    Order load(UUID uuid);
}
