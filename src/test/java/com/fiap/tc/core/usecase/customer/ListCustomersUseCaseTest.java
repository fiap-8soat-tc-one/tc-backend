package com.fiap.tc.core.usecase.customer;

import com.fiap.tc.core.application.ports.out.customer.ListCustomersOutputPort;
import com.fiap.tc.core.application.usecase.customer.ListCustomersUseCase;
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
    private ListCustomersOutputPort listCustomersOutputPort;

    @InjectMocks
    private ListCustomersUseCase listCustomersUseCase;

    @Test
    public void listCustomersTest() {
        listCustomersUseCase.list(Mockito.any());
        verify(listCustomersOutputPort).list(Mockito.any());
    }

}