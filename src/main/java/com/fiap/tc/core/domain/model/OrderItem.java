package com.fiap.tc.core.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItem {
    private String name;
    private Integer quantity;
    private BigDecimal unitValue;
    private BigDecimal total;
}
