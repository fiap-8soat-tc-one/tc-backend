package com.fiap.tc.adapters.driver.presentation.response;

import com.fiap.tc.core.domain.fixed.OrderStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderListResponse {

    @ApiModelProperty(
            value = "Order Id",
            example = "7ba2a960-2354-466f-8868-6ad713742407",
            dataType = "UUID"
    )
    private UUID id;

    @ApiModelProperty(
            value = "Order Number",
            example = "EfhxLZ",
            dataType = "String"
    )
    private String orderNumber;

    @ApiModelProperty(
            value = "Order status",
            required = true,
            example = "READY",
            dataType = "enum"

    )
    private OrderStatus status;

    @ApiModelProperty(
            value = "Order wait time in minutes",
            required = true,
            example = "10",
            dataType = "Optional<Integer>"
    )
    private Long waitTime;


}
