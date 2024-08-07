package com.fiap.tc.application.port.in.order;

import com.fiap.tc.infrastructure.adapter.web.responses.OrderResponse;

import java.util.UUID;

public interface LoadOrderInputPort {
    OrderResponse load(UUID uuid);
}
