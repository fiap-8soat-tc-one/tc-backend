package com.fiap.tc.adapters.repository.output.validator.order.concrete;

import com.fiap.tc.core.domain.exception.BadRequestException;
import com.fiap.tc.core.domain.model.enums.OrderStatus;
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