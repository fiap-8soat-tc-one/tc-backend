package com.fiap.tc.infrastructure.gateways.validators.order.concrete;

import com.fiap.tc.infrastructure.gateways.validators.order.concrete.ReadyOrderStatusValidator;
import com.fiap.tc.domain.exceptions.BadRequestException;
import com.fiap.tc.domain.enums.OrderStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ReadyOrderStatusValidatorTest {
    @Test
    public void transitionToFinishedAllowedStatusTest() {
        new ReadyOrderStatusValidator().validate(OrderStatus.FINISHED);
    }

    @Test
    public void transitionToCanceledAllowedStatusTest() {
        new ReadyOrderStatusValidator().validate(OrderStatus.CANCELED);
    }

    @Test
    public void transitionToPendingInvalidStatusTest() {
        var assertThrows = assertThrows(BadRequestException.class,
                () -> new ReadyOrderStatusValidator().validate(OrderStatus.PENDING));

        assertTrue(assertThrows.getMessage().contains("invalid next state"));
    }

}