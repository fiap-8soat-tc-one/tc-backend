package com.fiap.tc.adapters.repository.output.validator.order.concrete;

import com.fiap.tc.core.domain.model.enums.OrderStatus;

import java.util.Collections;
import java.util.List;


public class FinishedOrderStatusValidator extends OrderStatusValidatorTemplate {
    @Override
    public List<OrderStatus> next() {
        return Collections.emptyList();
    }
}
