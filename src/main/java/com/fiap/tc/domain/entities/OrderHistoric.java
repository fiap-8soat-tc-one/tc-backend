package com.fiap.tc.domain.entities;

import com.fiap.tc.domain.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderHistoric {
    private OrderStatus status;
    private LocalDateTime registerDate;
}
