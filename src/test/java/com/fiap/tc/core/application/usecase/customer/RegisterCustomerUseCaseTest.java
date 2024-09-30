package com.fiap.tc.core.application.usecase.customer;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.application.gateways.CustomerGatewaySpec;
import com.fiap.tc.application.usecases.customer.RegisterCustomerUseCase;
import com.fiap.tc.domain.entities.Customer;
import com.fiap.tc.fixture.FixtureTest;
import com.fiap.tc.infrastructure.presentation.requests.CustomerRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegisterCustomerUseCaseTest extends FixtureTest {
    @Mock
    private CustomerGatewaySpec customerGateway;

    @InjectMocks
    private RegisterCustomerUseCase registerCustomerUseCase;

    private CustomerRequest request;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        request = Fixture.from(CustomerRequest.class).gimme("valid");
        customer = Fixture.from(Customer.class).gimme("valid");
    }

    @Test
    public void registerCustomerTest() {
        when(customerGateway.saveOrUpdate(anyString(), anyString(), anyString())).thenReturn(customer);
        var customerResult = registerCustomerUseCase.register(anyString(), anyString(), anyString());
        assertEquals(customer, customerResult);
        verify(customerGateway).saveOrUpdate(anyString(), anyString(), anyString());
    }
}
