package com.fiap.tc.core.application.usecase.order;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.adapters.driver.presentation.requests.OrderStatusRequest;
import com.fiap.tc.core.application.ports.out.order.UpdateStatusOrderOutputPort;
import com.fiap.tc.fixture.FixtureTest;
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
    private UpdateStatusOrderOutputPort updateStatusOrderOutputPort;

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
        verify(updateStatusOrderOutputPort).update(request.getId(), request.getStatus());
    }

}