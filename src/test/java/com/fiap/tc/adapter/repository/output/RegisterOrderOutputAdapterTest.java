package com.fiap.tc.adapter.repository.output;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.adapter.repository.CustomerRepository;
import com.fiap.tc.adapter.repository.OrderRepository;
import com.fiap.tc.adapter.repository.ProductRepository;
import com.fiap.tc.adapter.repository.entity.CustomerEntity;
import com.fiap.tc.adapter.repository.entity.OrderEntity;
import com.fiap.tc.adapter.repository.entity.ProductEntity;
import com.fiap.tc.core.domain.exception.NotFoundException;
import com.fiap.tc.core.domain.requests.OrderRequest;
import com.fiap.tc.util.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegisterOrderOutputAdapterTest extends BaseTest {

    public static final UUID UUID = java.util.UUID.randomUUID();

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private RegisterOrderOutputAdapter registerOrderOutputAdapter;

    private OrderEntity orderEntity;

    private OrderRequest orderRequest;

    private ProductEntity productEntity;

    private CustomerEntity customerEntity;

    @BeforeEach
    public void setUp() {
        orderEntity = Fixture.from(OrderEntity.class).gimme("valid");
        orderRequest = Fixture.from(OrderRequest.class).gimme("valid");
        productEntity = Fixture.from(ProductEntity.class).gimme("valid");
        customerEntity = Fixture.from(CustomerEntity.class).gimme("valid");
    }


    @Test
    public void saveOrderTest() {
        var idProduct = orderRequest.getOrderItems().get(0).getIdProduct();

        when(orderRepository.save(Mockito.any())).thenReturn(orderEntity);
        when(customerRepository.findByUuid(orderRequest.getIdCustomer())).thenReturn(Optional.of(customerEntity));
        when(productRepository.findByUuid(idProduct)).thenReturn(Optional.of(productEntity));

        var order = registerOrderOutputAdapter.save(orderRequest.getIdCustomer(), orderRequest.getOrderItems());

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

        var order = registerOrderOutputAdapter.save(orderRequest.getIdCustomer(), orderRequest.getOrderItems());

        assertNotNull(order);
        verify(customerRepository).findByUuid(orderRequest.getIdCustomer());
        verify(orderRepository).save(Mockito.any());

    }

    @Test
    public void launchExceptionWhenProductNotExistTest() {

        var idProduct = orderRequest.getOrderItems().get(0).getIdProduct();

        when(customerRepository.findByUuid(orderRequest.getIdCustomer())).thenReturn(Optional.empty());
        when(productRepository.findByUuid(idProduct)).thenReturn(Optional.empty());

        var assertThrows = Assertions.assertThrows(NotFoundException.class,
                () -> registerOrderOutputAdapter.save(orderRequest.getIdCustomer(), orderRequest.getOrderItems()));

        assertTrue(assertThrows.getMessage().contains("not found"));
    }


}