package com.fiap.tc.adapters.repository.output.validator.order.concrete;

import com.fiap.tc.adapters.driven.infrastructure.outputs.validators.order.concrete.PreparingOrderStatusValidator;
import com.fiap.tc.core.domain.exceptions.BadRequestException;
import com.fiap.tc.core.domain.fixed.OrderStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class PreparingOrderStatusValidatorTest {
    @Test
    public void transitionToPreparingAllowedStatusTest() {
        new PreparingOrderStatusValidator().validate(OrderStatus.READY);
    }

    @Test
    public void transitionToCanceledAllowedStatusTest() {
        new PreparingOrderStatusValidator().validate(OrderStatus.CANCELED);
    }

    @Test
    public void transitionToReadyInvalidStatusTest() {
        var assertThrows = assertThrows(BadRequestException.class,
                () -> new PreparingOrderStatusValidator().validate(OrderStatus.PREPARING));

        assertTrue(assertThrows.getMessage().contains("invalid next state"));
    }

}