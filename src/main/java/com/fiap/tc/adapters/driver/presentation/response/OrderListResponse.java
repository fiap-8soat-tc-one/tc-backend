package com.fiap.tc.adapters.driver.presentation.response;

import com.fiap.tc.core.domain.enums.OrderStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
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
            value = "Order number to customer follow up",
            example = "ed6l",
            dataType = "String"
    )
    private String orderNumber;

    @ApiModelProperty(
            value = "Order status",
            example = "READY",
            dataType = "enum"

    )
    private OrderStatus status;

    @ApiModelProperty(
            value = "Order last status updated",
            example = "2024-09-18T15:27:22.493994",
            dataType = "LocalDatetime"

    )
    private LocalDateTime updatedDate;

    @ApiModelProperty(
            value = "Order wait time in minutes",
            example = "10",
            dataType = "Optional<Integer>"
    )
    private Long waitTime;

}
