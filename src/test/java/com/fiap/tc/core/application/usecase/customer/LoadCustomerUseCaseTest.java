package com.fiap.tc.core.application.usecase.customer;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.application.gateways.CustomerGatewaySpec;
import com.fiap.tc.application.usecases.customer.LoadCustomerUseCase;
import com.fiap.tc.domain.entities.Customer;
import com.fiap.tc.fixture.FixtureTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoadCustomerUseCaseTest extends FixtureTest {

    public static final String DOCUMENT = "11111111111";
    @Mock
    private CustomerGatewaySpec customerGateway;

    @InjectMocks
    private LoadCustomerUseCase loadCustomerUseCase;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = Fixture.from(Customer.class).gimme("valid");
    }

    @Test
    public void loadCustomer() {
        when(customerGateway.load(anyString())).thenReturn(customer);

        var customerResult = loadCustomerUseCase.load(DOCUMENT);

        assertEquals(customer, customerResult);
        Mockito.verify(customerGateway).load(anyString());
    }


}