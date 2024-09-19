package com.fiap.tc.core.application.usecase.product;

import com.fiap.tc.core.application.usecase.ports.out.product.ListProductsByCategoryOutputPort;
import com.fiap.tc.fixture.FixtureTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ListProductsByCategoryUseCaseTest extends FixtureTest {

    @Mock
    private ListProductsByCategoryOutputPort listProductsByCategoryOutputPort;

    @InjectMocks
    private ListProductsByCategoryUseCase listProductsByCategoryUseCase;

    @Test
    public void listProductsByCategoryTest() {
        listProductsByCategoryUseCase.list(Mockito.any(), Mockito.any());
        verify(listProductsByCategoryOutputPort).list(Mockito.any(), Mockito.any());
    }


}