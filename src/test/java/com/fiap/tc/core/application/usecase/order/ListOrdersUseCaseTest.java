package com.fiap.tc.core.application.usecase.order;

import com.fiap.tc.application.gateways.OrderGatewaySpec;
import com.fiap.tc.application.usecases.order.ListOrdersUseCase;
import com.fiap.tc.fixture.FixtureTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.fiap.tc.domain.enums.OrderStatus.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ListOrdersUseCaseTest extends FixtureTest {

    @Mock
    private OrderGatewaySpec orderGateway;

    @InjectMocks
    private ListOrdersUseCase listOrdersUseCase;

    @Test
    public void listOrdersTest() {
        var pageable = Mockito.mock(Pageable.class);
        listOrdersUseCase.list(pageable);
        verify(orderGateway).list(List.of(READY.name(), PREPARING.name(), RECEIVED.name()),
                pageable);
    }
}