package com.fiap.tc.infrastructure.gateways;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.domain.entities.Order;
import com.fiap.tc.domain.enums.OrderStatus;
import com.fiap.tc.fixture.FixtureTest;
import com.fiap.tc.infrastructure.core.utils.QRCodeGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PaymentLinkGatewayTests extends FixtureTest {

    @Mock
    private QRCodeGenerator qrCodeGenerator;

    @InjectMocks
    private PaymentLinkGateway paymentLinkGateway;

    private Order order;

    @BeforeEach
    public void setUp() {
        order = Fixture.from(Order.class).gimme("valid");
    }

    @Test
    public void generatePaymentLinkTest() {
        Mockito.when(qrCodeGenerator.generate(Mockito.anyString())).thenReturn("paymentLink");
        var paymentLinkOptional = paymentLinkGateway.generate(order);
        Assertions.assertTrue(paymentLinkOptional.isPresent());
        Mockito.verify(qrCodeGenerator).generate(Mockito.anyString());
    }

    @Test
    public void generateWithoutPaymentLinkTest() {
        Mockito.when(qrCodeGenerator.generate(Mockito.anyString())).thenReturn(null);
        var paymentLinkOptional = paymentLinkGateway.generate(order);
        Assertions.assertTrue(paymentLinkOptional.isEmpty());
        Mockito.verify(qrCodeGenerator).generate(Mockito.anyString());
    }

    @Test
    public void generateWithoutPaymentLinkWhenInvalidOrderStatusTest() {
        order.setStatus(OrderStatus.PREPARING);
        var paymentLinkOptional = paymentLinkGateway.generate(order);
        Assertions.assertTrue(paymentLinkOptional.isEmpty());
        Mockito.verify(qrCodeGenerator, Mockito.times(0)).generate(Mockito.anyString());
    }

}