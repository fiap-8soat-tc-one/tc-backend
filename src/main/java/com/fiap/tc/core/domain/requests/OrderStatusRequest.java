package com.fiap.tc.core.domain.requests;

import com.fiap.tc.core.domain.model.enums.OrderStatus;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Data
public class OrderStatusRequest {
    private UUID id;
    private OrderStatus status;
}
