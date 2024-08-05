package com.fiap.tc.core.usecase.customer;

import com.fiap.tc.core.port.out.customer.ListCustomersOutputPort;
import com.fiap.tc.util.BaseTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ListCustomersUseCaseTest extends BaseTest {

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