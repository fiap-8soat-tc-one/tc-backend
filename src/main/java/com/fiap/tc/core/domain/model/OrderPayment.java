package com.fiap.tc.core.domain.model;

import com.fiap.tc.core.domain.model.enums.PaymentResult;
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
