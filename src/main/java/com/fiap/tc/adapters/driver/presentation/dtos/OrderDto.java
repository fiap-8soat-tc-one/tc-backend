package com.fiap.tc.adapters.driver.presentation.dtos;

import com.fiap.tc.core.domain.enums.OrderStatus;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {
    private UUID id;
    private String orderNumber;
    private BigDecimal total;
    private OrderStatus status;
    private List<OrderItemDto> items;
    private List<OrderHistoricDto> orderHistoric;

    @JsonIgnore
    public String orderWithTotalAsText() {
        return id + "-" + total;
    }
}
