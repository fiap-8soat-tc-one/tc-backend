package com.fiap.tc.infrastructure.presentation.response;

import com.fiap.tc.infrastructure.presentation.dtos.OrderHistoricDto;
import com.fiap.tc.infrastructure.presentation.dtos.OrderItemDto;
import com.fiap.tc.domain.enums.OrderStatus;
import io.swagger.annotations.ApiModelProperty;
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
            value = "Order total amount",
            example = "75.55",
            dataType = "BigDecimal"
    )
    private BigDecimal total;

    @ApiModelProperty(
            value = "Order status",
            example = "RECEIVED",
            dataType = "String",
            allowableValues = "RECEIVED, PENDING, PREPARING, READY, FINISHED, CANCELED"
    )
    private OrderStatus status;

    @ApiModelProperty(
            value = "Order items",
            dataType = "List<OrderItemDto>"
    )
    private List<OrderItemDto> items;

    @ApiModelProperty(
            value = "Order Status historic transitions",
            dataType = "List<OrderHistoricDto>"
    )
    private List<OrderHistoricDto> orderHistoric;

    @ApiModelProperty(
            value = "Order payment link qrcode base64 encoded",
            dataType = "String"
    )
    private String paymentLink;


    @JsonIgnore
    public String orderWithTotalAsText() {
        return id + "-" + total;
    }
}
