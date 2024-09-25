package com.fiap.tc.core.application.usecase.order;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.application.gateways.OrderGatewaySpec;
import com.fiap.tc.application.gateways.PaymentLinkGatewaySpec;
import com.fiap.tc.application.usecases.order.RegisterOrderUseCase;
import com.fiap.tc.fixture.FixtureTest;
import com.fiap.tc.infrastructure.gateways.mappers.base.MapperConstants;
import com.fiap.tc.infrastructure.persistence.entities.OrderEntity;
import com.fiap.tc.infrastructure.presentation.requests.OrderRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.fiap.tc.infrastructure.presentation.mappers.base.MapperConstants.ORDER_ITEM_MAPPER;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegisterOrderUseCaseTest extends FixtureTest {

    @Mock
    private OrderGatewaySpec orderGateway;

    @Mock
    private PaymentLinkGatewaySpec paymentLinkGateway;

    @InjectMocks
    private RegisterOrderUseCase registerOrderUseCase;

    private OrderRequest request;
    private OrderEntity orderEntity;

    @BeforeEach
    public void setUp() {
        request = Fixture.from(OrderRequest.class).gimme("valid");
        orderEntity = Fixture.from(OrderEntity.class).gimme("valid");
    }

    @Test
    public void registerOrderTest() {
        var order = MapperConstants.ORDER_MAPPER.fromEntity(orderEntity);
        var orderList = request.getOrderItems().stream().map(ORDER_ITEM_MAPPER::toDomain).toList();
        when(paymentLinkGateway.generate(order)).thenReturn(Optional.of("payment link"));
        when(orderGateway.register(request.getIdCustomer(), orderList)).thenReturn(order);

        var orderResponse = registerOrderUseCase.register(request.getIdCustomer(), orderList);

        assertNotNull(orderResponse);
        verify(orderGateway).register(request.getIdCustomer(), orderList);
        verify(paymentLinkGateway).generate(Mockito.any());
    }
}