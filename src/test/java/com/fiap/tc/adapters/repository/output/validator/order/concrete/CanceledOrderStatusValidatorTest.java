package com.fiap.tc.adapters.repository.output.validator.order.concrete;

import com.fiap.tc.adapters.driven.infrastructure.outputs.validators.order.concrete.CanceledOrderStatusValidator;
import com.fiap.tc.core.domain.exceptions.BadRequestException;
import com.fiap.tc.core.domain.enums.OrderStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class CanceledOrderStatusValidatorTest {
    @Test
    public void transitionToCanceledInvalidStatusTest() {
        var assertThrows = assertThrows(BadRequestException.class,
                () -> new CanceledOrderStatusValidator().validate(OrderStatus.CANCELED));

        assertTrue(assertThrows.getMessage().contains("invalid next state"));
    }

    @Test
    public void transitionToPendingInvalidStatusTest() {
        var assertThrows = assertThrows(BadRequestException.class,
                () -> new CanceledOrderStatusValidator().validate(OrderStatus.PENDING));

        assertTrue(assertThrows.getMessage().contains("invalid next state"));
    }

}