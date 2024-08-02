package com.fiap.tc.core.domain.model;

import com.fiap.tc.core.domain.model.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderHistoric {
    private OrderStatus status;
    private LocalDateTime registerDate;
}
