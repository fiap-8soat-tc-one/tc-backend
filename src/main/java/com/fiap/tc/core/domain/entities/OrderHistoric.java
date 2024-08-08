package com.fiap.tc.core.domain.entities;

import com.fiap.tc.core.domain.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderHistoric {
    private OrderStatus status;
    private LocalDateTime registerDate;
}
