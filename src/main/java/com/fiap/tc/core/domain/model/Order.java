package com.fiap.tc.core.domain.model;

import com.fiap.tc.core.domain.enums.OrderStatus;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class Order {

    private UUID id;
    private BigDecimal total;
    private OrderStatus status;
    private List<OrderItem> items;
    private List<OrderHistoric> orderHistoric;

    @JsonIgnore
    public String orderWithTotalAsText() {
        var text = new StringBuilder();
        text.append(id).append("-").append(total);
        return text.toString();
    }
}
