package com.fiap.tc.adapters.driven.infrastructure.outputs;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.adapters.driven.infrastructure.utils.QRCodeGenerator;
import com.fiap.tc.core.domain.entities.Order;
import com.fiap.tc.core.domain.enums.OrderStatus;
import com.fiap.tc.fixture.FixtureTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PaymentLinkOutputAdapterTest extends FixtureTest {

    @Mock
    private QRCodeGenerator qrCodeGenerator;

    @InjectMocks
    private PaymentLinkOutputAdapter paymentLinkOutputAdapter;

    private Order order;

    @BeforeEach
    public void setUp() {
        order = Fixture.from(Order.class).gimme("valid");
    }

    @Test
    public void generatePaymentLinkTest() {
        Mockito.when(qrCodeGenerator.generate(Mockito.anyString())).thenReturn("paymentLink");
        var paymentLinkOptional = paymentLinkOutputAdapter.generate(order);
        Assertions.assertTrue(paymentLinkOptional.isPresent());
        Mockito.verify(qrCodeGenerator).generate(Mockito.anyString());
    }

    @Test
    public void generateWithoutPaymentLinkTest() {
        Mockito.when(qrCodeGenerator.generate(Mockito.anyString())).thenReturn(null);
        var paymentLinkOptional = paymentLinkOutputAdapter.generate(order);
        Assertions.assertTrue(paymentLinkOptional.isEmpty());
        Mockito.verify(qrCodeGenerator).generate(Mockito.anyString());
    }

    @Test
    public void generateWithoutPaymentLinkWhenInvalidOrderStatusTest() {
        order.setStatus(OrderStatus.PREPARING);
        var paymentLinkOptional = paymentLinkOutputAdapter.generate(order);
        Assertions.assertTrue(paymentLinkOptional.isEmpty());
        Mockito.verify(qrCodeGenerator, Mockito.times(0)).generate(Mockito.anyString());
    }

}