package com.fiap.tc.core.application.usecase.customer;

import com.fiap.tc.application.gateways.CustomerGatewaySpec;
import com.fiap.tc.application.usecases.customer.DeleteCustomerUseCase;
import com.fiap.tc.fixture.FixtureTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class DeleteCustomerUseCaseTest extends FixtureTest {

    public static final String DOCUMENT = "11111111111";
    @Mock
    private CustomerGatewaySpec customerGateway;

    @InjectMocks
    private DeleteCustomerUseCase deleteCustomerUseCase;

    @Test
    public void deleteCustomerTest() {
        deleteCustomerUseCase.delete(DOCUMENT);
        Mockito.verify(customerGateway).delete(anyString());
    }
}