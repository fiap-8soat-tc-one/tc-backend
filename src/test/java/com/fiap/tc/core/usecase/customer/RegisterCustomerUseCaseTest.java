package com.fiap.tc.core.usecase.customer;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.core.application.usecase.customer.RegisterCustomerUseCase;
import com.fiap.tc.core.domain.entities.Customer;
import com.fiap.tc.adapters.driver.presentation.requests.CustomerRequest;
import com.fiap.tc.core.application.ports.out.customer.SaveCustomerOutputPort;
import com.fiap.tc.fixture.FixtureTest;
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
    private SaveCustomerOutputPort saveCustomerOutputPort;

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
        when(saveCustomerOutputPort.saveOrUpdate(anyString(), anyString(), anyString())).thenReturn(customer);
        var customerResult = registerCustomerUseCase.register(request);
        assertEquals(customer, customerResult);
        verify(saveCustomerOutputPort).saveOrUpdate(anyString(), anyString(), anyString());
    }
}
