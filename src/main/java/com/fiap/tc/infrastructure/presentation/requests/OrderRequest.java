package com.fiap.tc.infrastructure.presentation.requests;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
public class OrderRequest {
    
    @NotNull
    @NotEmpty
    @Valid
    @ApiModelProperty(
            value = "Order items request",
            required = true,
            dataType = "List<OrderItemRequest>"
    )
    private List<OrderItemRequest> orderItems;

    @ApiModelProperty(
            value = "Customer Id",
            example = "7ba2a960-2354-466f-8868-6ad713742407",
            dataType = "UUID"
    )
    private UUID idCustomer;
}
