package com.fiap.tc.infrastructure.gateways;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.domain.enums.OrderStatus;
import com.fiap.tc.domain.exceptions.NotFoundException;
import com.fiap.tc.fixture.FixtureTest;
import com.fiap.tc.infrastructure.persistence.entities.CustomerEntity;
import com.fiap.tc.infrastructure.persistence.entities.OrderEntity;
import com.fiap.tc.infrastructure.persistence.entities.ProductEntity;
import com.fiap.tc.infrastructure.persistence.repositories.CustomerRepository;
import com.fiap.tc.infrastructure.persistence.repositories.OrderRepository;
import com.fiap.tc.infrastructure.persistence.repositories.ProductRepository;
import com.fiap.tc.infrastructure.presentation.requests.OrderRequest;
import com.fiap.tc.infrastructure.presentation.requests.OrderStatusRequest;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.fiap.tc.infrastructure.presentation.mappers.base.MapperConstants.ORDER_ITEM_MAPPER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderGatewayTests extends FixtureTest {

    public static final UUID UUID = java.util.UUID.randomUUID();

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private OrderGateway orderGateway;

    private OrderStatusRequest statusRequest;

    private List<String> status;

    private OrderEntity orderEntity;

    private ProductEntity productEntity;

    private CustomerEntity customerEntity;

    private OrderRequest orderRequest;

    private Pageable pageable;

    @BeforeEach
    public void setUp() {
        orderEntity = Fixture.from(OrderEntity.class).gimme("valid");
        productEntity = Fixture.from(ProductEntity.class).gimme("valid");
        customerEntity = Fixture.from(CustomerEntity.class).gimme("valid");
        orderRequest = Fixture.from(OrderRequest.class).gimme("valid");
        pageable = Mockito.mock(Pageable.class);
        statusRequest = Fixture.from(OrderStatusRequest.class).gimme("valid");
    }

    @Test
    public void listOrdersByStatusTest() {

        final var orderEntities = new PageImpl<>(List.of(orderEntity));
        when(orderRepository.findByStatus(status, pageable)).thenReturn(orderEntities);

        var ordersPageable = orderGateway.list(status, pageable);

        assertEquals(1, ordersPageable.getSize());
        verify(orderRepository).findByStatus(status, pageable);

    }

    @Test
    public void loadOrderTest() {
        when(orderRepository.findByUuid(UUID)).thenReturn(Optional.of(orderEntity));

        var order = orderGateway.load(UUID);
        assertNotNull(order);
        verify(orderRepository).findByUuid(UUID);

    }

    @Test
    public void loadOrderWhenOrderNotFoundTest() {

        when(orderRepository.findByUuid(UUID)).thenReturn(Optional.empty());

        var assertThrows = Assertions.assertThrows(NotFoundException.class,
                () -> orderGateway.load(UUID));

        assertTrue(assertThrows.getMessage().contains("not found"));
    }

    @Test
    public void updateOrderStatusWhenOrderNotFoundTest() {

        when(orderRepository.findByUuid(statusRequest.getId())).thenReturn(Optional.empty());

        var assertThrows = Assertions.assertThrows(NotFoundException.class,
                () -> orderGateway.updateStatus(statusRequest.getId(), statusRequest.getStatus()));

        assertTrue(assertThrows.getMessage().contains("not found"));
    }

    @Test
    public void updateOrderStatusTest() {
        orderEntity.setStatus(OrderStatus.RECEIVED);
        statusRequest.setStatus(OrderStatus.PREPARING);

        when(orderRepository.findByUuid(statusRequest.getId())).thenReturn(Optional.of(orderEntity));
        when(orderRepository.save(Mockito.any())).thenReturn(orderEntity);

        orderGateway.updateStatus(statusRequest.getId(), statusRequest.getStatus());

        verify(orderRepository).findByUuid(statusRequest.getId());
        verify(orderRepository).save(Mockito.any());
    }

    @Test
    public void saveOrderTest() {
        var idProduct = orderRequest.getOrderItems().get(0).getIdProduct();

        when(orderRepository.save(Mockito.any())).thenReturn(orderEntity);
        when(customerRepository.findByUuid(orderRequest.getIdCustomer())).thenReturn(Optional.of(customerEntity));
        when(productRepository.findByUuid(idProduct)).thenReturn(Optional.of(productEntity));

        var orderList = orderRequest.getOrderItems().stream().map(ORDER_ITEM_MAPPER::toDomain).toList();
        var order = orderGateway.register(orderRequest.getIdCustomer(), orderList);

        assertNotNull(order);
        verify(customerRepository).findByUuid(orderRequest.getIdCustomer());
        verify(productRepository, times(2)).findByUuid(idProduct);
        verify(orderRepository).save(Mockito.any());

    }

    @Test
    public void saveOrderWhenEmptyItemsTest() {
        orderRequest.setOrderItems(Collections.emptyList());
        when(orderRepository.save(Mockito.any())).thenReturn(orderEntity);
        when(customerRepository.findByUuid(orderRequest.getIdCustomer())).thenReturn(Optional.empty());

        var orderList = orderRequest.getOrderItems().stream().map(ORDER_ITEM_MAPPER::toDomain).toList();
        var order = orderGateway.register(orderRequest.getIdCustomer(), orderList);

        assertNotNull(order);
        verify(customerRepository).findByUuid(orderRequest.getIdCustomer());
        verify(orderRepository, times(1)).save(Mockito.any());

    }

    @Test
    public void launchExceptionWhenProductNotExistTest() {

        var idProduct = orderRequest.getOrderItems().get(0).getIdProduct();

        when(customerRepository.findByUuid(orderRequest.getIdCustomer())).thenReturn(Optional.empty());
        when(productRepository.findByUuid(idProduct)).thenReturn(Optional.empty());

        var orderList = orderRequest.getOrderItems().stream().map(ORDER_ITEM_MAPPER::toDomain).toList();
        var assertThrows = Assertions.assertThrows(NotFoundException.class,
                () -> orderGateway.register(orderRequest.getIdCustomer(), orderList));

        assertTrue(assertThrows.getMessage().contains("not found"));
    }

}