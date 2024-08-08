package com.fiap.tc.adapters.driver.presentation.requests;

import com.fiap.tc.core.domain.enums.OrderStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class OrderStatusRequest {
    @NotNull
    private UUID id;
    @NotNull
    private OrderStatus status;
}