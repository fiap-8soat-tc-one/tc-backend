package com.fiap.tc.core.application.usecase.order;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.application.gateways.OrderGatewaySpec;
import com.fiap.tc.application.usecases.order.UpdateStatusOrderUseCase;
import com.fiap.tc.fixture.FixtureTest;
import com.fiap.tc.infrastructure.presentation.requests.OrderStatusRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UpdateStatusOrderUseCaseTest extends FixtureTest {

    @Mock
    private OrderGatewaySpec orderGateway;

    @InjectMocks
    private UpdateStatusOrderUseCase updateStatusOrderUseCase;

    private OrderStatusRequest request;

    @BeforeEach
    public void setUp() {
        request = Fixture.from(OrderStatusRequest.class).gimme("valid");
    }

    @Test
    public void updateTest() {

        updateStatusOrderUseCase.update(request.getId(), request.getStatus());
        verify(orderGateway).updateStatus(request.getId(), request.getStatus());
    }

}