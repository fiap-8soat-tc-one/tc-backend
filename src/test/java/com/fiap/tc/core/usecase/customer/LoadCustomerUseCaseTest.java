package com.fiap.tc.core.usecase.customer;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.application.port.out.customer.LoadCustomerOutputPort;
import com.fiap.tc.application.usecase.customer.LoadCustomerUseCase;
import com.fiap.tc.core.domain.model.Customer;
import com.fiap.tc.util.BaseTest;
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
public class LoadCustomerUseCaseTest extends BaseTest {

    public static final String DOCUMENT = "11111111111";
    @Mock
    private LoadCustomerOutputPort loadCustomerOutputPort;

    @InjectMocks
    private LoadCustomerUseCase loadCustomerUseCase;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = Fixture.from(Customer.class).gimme("valid");
    }

    @Test
    public void loadCustomer() {
        when(loadCustomerOutputPort.load(anyString())).thenReturn(customer);

        var customerResult = loadCustomerUseCase.load(DOCUMENT);

        assertEquals(customer, customerResult);
        Mockito.verify(loadCustomerOutputPort).load(anyString());
    }


}