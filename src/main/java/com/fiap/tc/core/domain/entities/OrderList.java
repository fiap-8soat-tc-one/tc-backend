package com.fiap.tc.core.domain.entities;

import com.fiap.tc.core.domain.enums.OrderStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderList {
    private UUID id;
    private String orderNumber;
    private OrderStatus status;
    private Long waitTime;
}
