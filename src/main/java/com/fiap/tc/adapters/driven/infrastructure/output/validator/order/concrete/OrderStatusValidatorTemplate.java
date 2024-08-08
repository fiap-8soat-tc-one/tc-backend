package com.fiap.tc.adapters.repository.output.validator.order.concrete;

import com.fiap.tc.adapters.repository.output.validator.order.OrderStatusValidator;
import com.fiap.tc.core.domain.exception.BadRequestException;
import com.fiap.tc.core.domain.model.enums.OrderStatus;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static java.lang.String.format;

@Slf4j
public abstract class OrderStatusValidatorTemplate implements OrderStatusValidator {
    @Override
    public void validate(OrderStatus status) {
        var allowedNextStatus = next();
        if (!allowedNextStatus.contains(status)) {
            throw new BadRequestException(format("Status %s is in a invalid next state!", status.name()));
        }

    }

    public abstract List<OrderStatus> next();
}
