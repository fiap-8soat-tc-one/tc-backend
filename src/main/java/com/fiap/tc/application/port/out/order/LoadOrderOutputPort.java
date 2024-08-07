package com.fiap.tc.application.port.out.order;

import com.fiap.tc.core.domain.model.Order;

import java.util.UUID;

public interface LoadOrderOutputPort {
    Order load(UUID uuid);
}

