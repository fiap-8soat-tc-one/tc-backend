package com.fiap.tc.adapters.driver.presentation.dtos;

import com.fiap.tc.core.domain.enums.PaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentHistoricDto {
    private PaymentStatus status;
    private LocalDateTime registerDate;
    private String transactionMessage;
}
