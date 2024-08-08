package com.fiap.tc.adapters.driver.presentation.requests;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class OrderItemRequest {

    @NotNull
    @ApiModelProperty(
            value = "Order Product Id",
            required = true,
            example = "7ba2a960-2354-466f-8868-6ad713742407",
            dataType = "UUID"
    )
    private UUID idProduct;

    @NotNull
    @Min(value = 1, message = "Invalid quantity")
    @ApiModelProperty(
            value = "Order quantity",
            example = "1",
            dataType = "int"
    )
    private Integer quantity;
}
