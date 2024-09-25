package com.fiap.tc.core.application.usecase.payment;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.application.usecases.payment.RegisterPaymentUseCase;
import com.fiap.tc.domain.entities.OrderPayment;
import com.fiap.tc.fixture.FixtureTest;
import com.fiap.tc.infrastructure.gateways.OrderGateway;
import com.fiap.tc.infrastructure.gateways.PaymentGateway;
import com.fiap.tc.infrastructure.presentation.requests.OrderPaymentRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegisterPaymentUseCaseTest extends FixtureTest {

    @Mock
    private PaymentGateway paymentGateway;

    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private RegisterPaymentUseCase registerPaymentUseCase;

    private OrderPaymentRequest orderPaymentRequest;
    private OrderPayment orderPayment;

    @BeforeEach
    public void setUp() {
        orderPaymentRequest = Fixture.from(OrderPaymentRequest.class).gimme("valid");
        orderPayment = Fixture.from(OrderPayment.class).gimme("valid");
    }

    @Test
    public void registerPaymentTest() {
        when(paymentGateway.register(orderPaymentRequest.getTransactionNumber(),
                orderPaymentRequest.getTransactionMessage(), orderPaymentRequest.getTransactionDocument(),
                orderPaymentRequest.getStatus(), orderPaymentRequest.getPaymentType(),
                orderPaymentRequest.getTotal())).thenReturn(orderPayment);

        registerPaymentUseCase.register(orderPaymentRequest.getTransactionNumber(),
                orderPaymentRequest.getTransactionMessage(), orderPaymentRequest.getTransactionDocument(),
                orderPaymentRequest.getStatus(), orderPaymentRequest.getPaymentType(), orderPaymentRequest.getTotal());

        verify(paymentGateway).register(orderPaymentRequest.getTransactionNumber(),
                orderPaymentRequest.getTransactionMessage(), orderPaymentRequest.getTransactionDocument(),
                orderPaymentRequest.getStatus(), orderPaymentRequest.getPaymentType(), orderPaymentRequest.getTotal());
        verify(orderGateway).updateStatus(any(), any());
    }


}