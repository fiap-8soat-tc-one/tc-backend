package com.fiap.tc.domain.entities;

import com.fiap.tc.domain.enums.PaymentStatus;
import com.fiap.tc.domain.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class
OrderPayment {
    private UUID id;
    private PaymentStatus status;
    private PaymentType paymentType;
    private List<PaymentHistoric> paymentHistoric;
    private String transactionMessage;
}
