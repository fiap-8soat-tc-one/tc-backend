package com.fiap.tc.core.application.usecase.order;

import com.fiap.tc.core.application.ports.out.order.ListOrdersOutputPort;
import com.fiap.tc.fixture.FixtureTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.fiap.tc.core.domain.enums.OrderStatus.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ListOrdersUseCaseTest extends FixtureTest {

    @Mock
    private ListOrdersOutputPort listOrdersOutputPort;

    @InjectMocks
    private ListOrdersUseCase listOrdersUseCase;

    @Test
    public void listOrdersTest() {
        var pageable = Mockito.mock(Pageable.class);
        listOrdersUseCase.list(pageable);
        verify(listOrdersOutputPort).list(List.of(READY.name(), PREPARING.name(), RECEIVED.name()),
                pageable);
    }
}