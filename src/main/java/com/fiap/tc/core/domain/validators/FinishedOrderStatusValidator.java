package com.fiap.tc.core.domain.validators;

import com.fiap.tc.core.domain.enums.OrderStatus;

import java.util.Collections;
import java.util.List;


public class FinishedOrderStatusValidator extends OrderStatusValidatorTemplate {
    @Override
    public List<OrderStatus> next() {
        return Collections.emptyList();
    }
}
