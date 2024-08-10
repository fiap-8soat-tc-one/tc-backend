package com.fiap.tc.core.usecase.order;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.OrderEntity;
import com.fiap.tc.adapters.driven.infrastructure.mappers.base.MapperConstants;
import com.fiap.tc.core.application.utils.QRCodeGenerator;
import com.fiap.tc.core.application.usecase.order.RegisterOrderUseCase;
import com.fiap.tc.adapters.driver.presentation.requests.OrderRequest;
import com.fiap.tc.core.application.ports.out.order.RegisterOrderOutputPort;
import com.fiap.tc.fixture.FixtureTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.fiap.tc.adapters.driver.presentation.mappers.base.MapperConstants.ORDER_ITEM_MAPPER;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegisterOrderUseCaseTest extends FixtureTest {

    @Mock
    private RegisterOrderOutputPort registerOrderOutputPort;

    @Mock
    private QRCodeGenerator qrCodeGenerator;

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
        var orderList = request.getOrderItems().stream().map(ORDER_ITEM_MAPPER ::toDomain).toList();
        
        when(registerOrderOutputPort.save(request.getIdCustomer(), orderList)).thenReturn(order);

        var orderResponse = registerOrderUseCase.register(request.getIdCustomer(), orderList);

        assertNotNull(orderResponse);
        verify(registerOrderOutputPort).save(request.getIdCustomer(), orderList);
    }
}