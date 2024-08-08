package com.fiap.tc.core.domain.entities;

import com.fiap.tc.core.domain.fixed.PaymentResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class
OrderPayment {
    private UUID id;
    private UUID idOrder;
    private PaymentResult result;
}
