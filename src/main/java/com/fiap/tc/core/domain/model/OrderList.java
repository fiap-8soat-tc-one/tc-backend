package com.fiap.tc.core.domain.model;

import com.fiap.tc.core.domain.enums.OrderStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderList {
    private UUID id;
    private OrderStatus status;
    private Long waitTime;
}
