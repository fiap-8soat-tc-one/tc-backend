package com.fiap.tc.adapter.repository.output.validator.order.concrete;

import com.fiap.tc.core.domain.exception.BadRequestException;
import com.fiap.tc.core.domain.model.enums.OrderStatus;
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