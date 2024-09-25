package com.fiap.tc.domain.entities;

import com.fiap.tc.domain.enums.PaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentHistoric {
    private PaymentStatus status;
    private LocalDateTime registerDate;
    private String transactionMessage;
}
