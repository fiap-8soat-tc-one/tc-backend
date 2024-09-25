package com.fiap.tc.domain.entities;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OrderItem {

    private UUID idProduct;
    private String name;
    private Integer quantity;
    private BigDecimal unitValue;
    private BigDecimal total;
}
