package com.fiap.tc.core.domain.validators;

import com.fiap.tc.core.domain.enums.OrderStatus;
import com.fiap.tc.core.exceptions.BadRequestException;
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
