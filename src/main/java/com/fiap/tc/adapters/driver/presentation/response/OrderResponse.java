package com.fiap.tc.adapters.driver.presentation.response;

import com.fiap.tc.adapters.driver.presentation.dtos.OrderHistoricDto;
import com.fiap.tc.adapters.driver.presentation.dtos.OrderItemDto;
import com.fiap.tc.core.domain.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private UUID id;
    private String orderNumber;
    private BigDecimal total;
    private OrderStatus status;
    private List<OrderItemDto> items;
    private List<OrderHistoricDto> orderHistoric;
    private String paymentLink;


    @JsonIgnore
    public String orderWithTotalAsText() {
        return id + "-" + total;
    }
}
