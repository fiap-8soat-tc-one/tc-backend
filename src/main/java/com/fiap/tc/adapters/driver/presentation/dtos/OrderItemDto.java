package com.fiap.tc.adapters.driver.presentation.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OrderItemDto {

    private UUID idProduct;
    private String name;
    private Integer quantity;
    private BigDecimal unitValue;
    private BigDecimal total;
}
