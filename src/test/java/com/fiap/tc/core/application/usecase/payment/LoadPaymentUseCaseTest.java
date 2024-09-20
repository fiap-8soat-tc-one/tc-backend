package com.fiap.tc.core.application.usecase.payment;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.core.application.ports.out.payment.LoadPaymentOutputPort;
import com.fiap.tc.core.domain.entities.OrderPayment;
import com.fiap.tc.fixture.FixtureTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoadPaymentUseCaseTest extends FixtureTest {

    @Mock
    private LoadPaymentOutputPort loadPaymentOutputPort;

    @InjectMocks
    private LoadPaymentUseCase loadPaymentUseCase;

    private OrderPayment orderPayment;

    @BeforeEach
    public void setUp() {
        orderPayment = Fixture.from(OrderPayment.class).gimme("valid");
    }

    @Test
    public void loadPaymentTest() {
        when(loadPaymentOutputPort.load(Mockito.any())).thenReturn(orderPayment);
        var paymentResult = loadPaymentUseCase.load(UUID.randomUUID());
        assertEquals(orderPayment, paymentResult);
    }


}