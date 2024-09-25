package com.fiap.tc.core.application.usecase.customer;

import com.fiap.tc.application.gateways.CustomerGatewaySpec;
import com.fiap.tc.application.usecases.customer.ListCustomersUseCase;
import com.fiap.tc.fixture.FixtureTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ListCustomersUseCaseTest extends FixtureTest {

    @Mock
    private CustomerGatewaySpec customerGateway;

    @InjectMocks
    private ListCustomersUseCase listCustomersUseCase;

    @Test
    public void listCustomersTest() {
        listCustomersUseCase.list(Mockito.any());
        verify(customerGateway).list(Mockito.any());
    }

}