package com.fiap.tc.adapters.repository.output.validator.order.concrete;

import com.fiap.tc.core.domain.exception.BadRequestException;
import com.fiap.tc.core.domain.model.enums.OrderStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class PendingOrderStatusValidatorTest {
    @Test
    public void transitionToPreparingAllowedStatusTest() {
        new PendingOrderStatusValidator().validate(OrderStatus.PREPARING);
    }

    @Test
    public void transitionToPendingAllowedStatusTest() {
        new PendingOrderStatusValidator().validate(OrderStatus.PENDING);
    }

    @Test
    public void transitionToCanceledAllowedStatusTest() {
        new PendingOrderStatusValidator().validate(OrderStatus.CANCELED);
    }

    @Test
    public void transitionToReadyInvalidStatusTest() {
        var assertThrows = assertThrows(BadRequestException.class,
                () -> new PendingOrderStatusValidator().validate(OrderStatus.READY));

        assertTrue(assertThrows.getMessage().contains("invalid next state"));
    }

}