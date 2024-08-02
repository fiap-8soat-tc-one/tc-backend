package com.fiap.tc.core.domain.model;

import com.fiap.tc.core.domain.model.enums.OrderStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class Order {
    @ApiModelProperty(
            value = "Order Id",
            example = "7ba2a960-2354-466f-8868-6ad713742407",
            dataType = "UUID"
    )
    private UUID id;

    @ApiModelProperty(
            value = "Order total value",
            example = "50.00",
            dataType = "BigDecimal"
    )
    private BigDecimal total;

    @ApiModelProperty(
            value = "Order status",
            required = true,
            example = "READY",
            dataType = "enum"

    )
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
