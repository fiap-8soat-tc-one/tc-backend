package com.fiap.tc.core.application.ports.in.order;

import com.fiap.tc.adapters.driver.presentation.response.OrderResponse;

import java.util.UUID;

public interface LoadOrderInputPort {
    OrderResponse load(UUID uuid);
}
