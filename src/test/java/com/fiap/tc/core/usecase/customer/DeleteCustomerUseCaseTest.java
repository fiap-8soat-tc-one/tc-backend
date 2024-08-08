package com.fiap.tc.core.usecase.customer;

import com.fiap.tc.core.application.port.out.customer.DeleteCustomerOutputPort;
import com.fiap.tc.core.application.usecase.customer.DeleteCustomerUseCase;
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
    private DeleteCustomerOutputPort deleteCustomerOutputPort;

    @InjectMocks
    private DeleteCustomerUseCase deleteCustomerUseCase;

    @Test
    public void deleteCustomerTest() {
        deleteCustomerUseCase.delete(DOCUMENT);
        Mockito.verify(deleteCustomerOutputPort).delete(anyString());
    }
}