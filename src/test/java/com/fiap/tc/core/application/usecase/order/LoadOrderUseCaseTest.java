package com.fiap.tc.core.application.usecase.order;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.application.gateways.OrderGatewaySpec;
import com.fiap.tc.application.gateways.PaymentLinkGatewaySpec;
import com.fiap.tc.application.usecases.order.LoadOrderUseCase;
import com.fiap.tc.domain.enums.OrderStatus;
import com.fiap.tc.fixture.FixtureTest;
import com.fiap.tc.infrastructure.gateways.mappers.base.MapperConstants;
import com.fiap.tc.infrastructure.persistence.entities.OrderEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoadOrderUseCaseTest extends FixtureTest {

    public static final UUID UUID = java.util.UUID.randomUUID();
    @Mock
    private OrderGatewaySpec orderGateway;

    @Mock
    private PaymentLinkGatewaySpec paymentLinkGateway;

    @InjectMocks
    private LoadOrderUseCase loadOrderUseCase;

    private OrderEntity orderEntity;

    @BeforeEach
    public void setUp() {
        orderEntity = Fixture.from(OrderEntity.class).gimme("valid");
    }

    @Test
    public void loadOrderTest() {
        var order = MapperConstants.ORDER_MAPPER.fromEntity(orderEntity);
        order.setStatus(OrderStatus.FINISHED);
        when(orderGateway.load(UUID)).thenReturn(order);
        when(paymentLinkGateway.generate(Mockito.any())).thenReturn(Optional.empty());

        var orderResult = loadOrderUseCase.load(UUID);

        assertEquals(order, orderResult);
        verify(orderGateway).load(UUID);
        verify(paymentLinkGateway).generate(Mockito.any());

    }

    @Test
    public void loadOrderWithPaymentLinkTest() {
        var order = MapperConstants.ORDER_MAPPER.fromEntity(orderEntity);
        order.setStatus(OrderStatus.RECEIVED);
        when(paymentLinkGateway.generate(order)).thenReturn(Optional.of("payment link"));
        when(orderGateway.load(UUID)).thenReturn(order);
        var orderResult = loadOrderUseCase.load(UUID);

        assertEquals(order, orderResult);
        verify(orderGateway).load(UUID);
        verify(paymentLinkGateway).generate(Mockito.any());
    }
}
