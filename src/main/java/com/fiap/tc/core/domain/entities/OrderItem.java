package com.fiap.tc.core.domain.entities;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItem {
    private String name;
    private Integer quantity;
    private BigDecimal unitValue;
    private BigDecimal total;
}
