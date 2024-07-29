package com.fiap.tc.core.port.in.order;

import com.fiap.tc.core.domain.response.OrderResponse;

import java.util.UUID;

public interface LoadOrderInputPort {
    OrderResponse load(UUID uuid);
}
