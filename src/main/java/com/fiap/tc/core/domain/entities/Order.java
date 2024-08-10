package com.fiap.tc.core.domain.entities;

import com.fiap.tc.core.domain.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class Order {
    private UUID id;
    private String orderNumber;
    private BigDecimal total;
    private OrderStatus status;
    private List<OrderItem> items;
    private List<OrderHistoric> orderHistoric;
    private String paymentLink;

    public String orderWithTotalAsText() {
        return id + "-" + total;
    }
}
