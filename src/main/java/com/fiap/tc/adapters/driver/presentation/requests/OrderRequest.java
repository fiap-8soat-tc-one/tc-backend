package com.fiap.tc.adapters.driver.presentation.requests;

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
    private List<OrderItemRequest> orderItems;

    private UUID idCustomer;
}
