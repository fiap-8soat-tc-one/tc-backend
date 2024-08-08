package com.fiap.tc.adapters.repository.output.validator.order.concrete;

import com.fiap.tc.core.domain.exception.BadRequestException;
import com.fiap.tc.core.domain.model.enums.OrderStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ReceivedOrderStatusValidatorTest {
    @Test
    public void transitionToPreparingAllowedStatusTest() {
        new ReceivedOrderStatusValidator().validate(OrderStatus.PREPARING);
    }

    @Test
    public void transitionToPendingAllowedStatusTest() {
        new ReceivedOrderStatusValidator().validate(OrderStatus.PENDING);
    }

    @Test
    public void transitionToCanceledAllowedStatusTest() {
        new ReceivedOrderStatusValidator().validate(OrderStatus.CANCELED);
    }

    @Test
    public void transitionToFinishedInvalidStatusTest() {
        var assertThrows = assertThrows(BadRequestException.class,
                () -> new ReceivedOrderStatusValidator().validate(OrderStatus.FINISHED));

        assertTrue(assertThrows.getMessage().contains("invalid next state"));
    }

}