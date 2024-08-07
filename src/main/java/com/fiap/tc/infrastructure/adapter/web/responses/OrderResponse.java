package com.fiap.tc.infrastructure.adapter.web.responses;

import com.fiap.tc.core.domain.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Order order;
    private String qrCodePaymentBase64;
    private Integer waitTime;
}
