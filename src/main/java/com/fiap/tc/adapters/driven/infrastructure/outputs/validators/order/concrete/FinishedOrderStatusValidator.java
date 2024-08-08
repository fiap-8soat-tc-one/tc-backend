package com.fiap.tc.adapters.driven.infrastructure.outputs.validators.order.concrete;

import com.fiap.tc.core.domain.model.enums.OrderStatus;

import java.util.Collections;
import java.util.List;


public class FinishedOrderStatusValidator extends OrderStatusValidatorTemplate {
    @Override
    public List<OrderStatus> next() {
        return Collections.emptyList();
    }
}
