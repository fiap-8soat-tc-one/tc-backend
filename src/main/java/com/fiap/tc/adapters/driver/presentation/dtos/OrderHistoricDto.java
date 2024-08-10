package com.fiap.tc.adapters.driver.presentation.dtos;

import com.fiap.tc.core.domain.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderHistoricDto {
    private OrderStatus status;
    private LocalDateTime registerDate;
}
