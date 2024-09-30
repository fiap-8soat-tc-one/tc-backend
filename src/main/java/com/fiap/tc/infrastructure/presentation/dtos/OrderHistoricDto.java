package com.fiap.tc.infrastructure.presentation.dtos;

import com.fiap.tc.domain.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderHistoricDto {
    private OrderStatus status;
    private LocalDateTime registerDate;
}
