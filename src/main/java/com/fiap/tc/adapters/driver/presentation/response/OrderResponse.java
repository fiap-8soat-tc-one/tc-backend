package com.fiap.tc.adapters.driver.presentation.response;

import com.fiap.tc.adapters.driver.presentation.dtos.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private OrderDto order;
    private String qrCodePaymentBase64;
    private Integer waitTime;
}
