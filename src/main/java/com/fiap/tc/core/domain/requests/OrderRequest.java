package com.fiap.tc.core.domain.requests;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Data
public class OrderRequest {
    @Valid
    private OrderPaymentRequest orderPaymentRequest;

    @Valid
    private List<OrderItemRequest> orderItems;

    private UUID idCustomer;
}
