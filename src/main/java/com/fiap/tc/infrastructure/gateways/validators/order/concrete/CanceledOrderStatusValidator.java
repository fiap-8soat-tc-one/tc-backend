package com.fiap.tc.infrastructure.gateways.validators.order.concrete;

import com.fiap.tc.domain.enums.OrderStatus;

import java.util.Collections;
import java.util.List;


public class CanceledOrderStatusValidator extends OrderStatusValidatorTemplate {
    @Override
    public List<OrderStatus> next() {
        return Collections.emptyList();
    }
}
