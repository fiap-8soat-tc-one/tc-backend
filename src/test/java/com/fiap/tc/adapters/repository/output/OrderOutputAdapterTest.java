package com.fiap.tc.adapters.repository.output;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.adapters.driven.infrastructure.outputs.OrderOutputAdapter;
import com.fiap.tc.adapters.driven.infrastructure.persistence.repositories.OrderRepository;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.OrderEntity;
import com.fiap.tc.core.domain.exceptions.NotFoundException;
import com.fiap.tc.core.domain.fixed.OrderStatus;
import com.fiap.tc.adapters.driver.presentation.requests.OrderStatusRequest;
import com.fiap.tc.fixture.FixtureTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderOutputAdapterTest extends FixtureTest {

    public static final UUID UUID = java.util.UUID.randomUUID();

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderOutputAdapter orderOutputAdapter;

    private OrderStatusRequest statusRequest;

    private List<OrderStatus> status;

    private OrderEntity orderEntity;

    private Pageable pageable;

    @BeforeEach
    public void setUp() {
        orderEntity = Fixture.from(OrderEntity.class).gimme("valid");
        statusRequest = Fixture.from(OrderStatusRequest.class).gimme("valid");
        pageable = Mockito.mock(Pageable.class);
    }

    @Test
    public void listOrdersByStatusTest() {

        final var orderEntities = new PageImpl<OrderEntity>(List.of(orderEntity));
        when(orderRepository.findByStatusIn(status, pageable)).thenReturn(orderEntities);

        var ordersPageable = orderOutputAdapter.list(status, pageable);

        assertEquals(1, ordersPageable.getSize());
        verify(orderRepository).findByStatusIn(status, pageable);

    }

    @Test
    public void loadOrderTest() {
        when(orderRepository.findByUuid(UUID)).thenReturn(Optional.of(orderEntity));

        var order = orderOutputAdapter.load(UUID);
        assertNotNull(order);
        verify(orderRepository).findByUuid(UUID);

    }

    @Test
    public void loadOrderWhenOrderNotFoundTest() {

        when(orderRepository.findByUuid(UUID)).thenReturn(Optional.empty());

        var assertThrows = Assertions.assertThrows(NotFoundException.class,
                () -> orderOutputAdapter.load(UUID));

        assertTrue(assertThrows.getMessage().contains("not found"));
    }

    @Test
    public void updateOrderStatusWhenOrderNotFoundTest() {

        when(orderRepository.findByUuid(statusRequest.getId())).thenReturn(Optional.empty());

        var assertThrows = Assertions.assertThrows(NotFoundException.class,
                () -> orderOutputAdapter.update(statusRequest.getId(), statusRequest.getStatus()));

        assertTrue(assertThrows.getMessage().contains("not found"));
    }

    @Test
    public void updateOrderStatusTest() {
        orderEntity.setStatus(OrderStatus.RECEIVED);
        statusRequest.setStatus(OrderStatus.PREPARING);
        when(orderRepository.findByUuid(statusRequest.getId())).thenReturn(Optional.of(orderEntity));
        when(orderRepository.save(Mockito.any())).thenReturn(orderEntity);

        orderOutputAdapter.update(statusRequest.getId(), statusRequest.getStatus());

        verify(orderRepository).findByUuid(statusRequest.getId());
        verify(orderRepository).save(Mockito.any());
    }

}