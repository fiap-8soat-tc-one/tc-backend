package com.fiap.tc.infrastructure.presentation.dtos;

import com.fiap.tc.domain.enums.PaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentHistoricDto {
    private PaymentStatus status;
    private LocalDateTime registerDate;
    private String transactionMessage;
}
