package com.fiap.tc.core.usecase.category;

import com.fiap.tc.core.port.out.category.ListCategoriesOutputPort;
import com.fiap.tc.util.BaseTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ListCategoriesUseCaseTest extends BaseTest {

    @Mock
    private ListCategoriesOutputPort listCategoriesOutputPort;

    @InjectMocks
    private ListCategoriesUseCase listCategoriesUseCase;

    @Test
    public void listCategoriesTest() {
        listCategoriesUseCase.list(Mockito.any());
        verify(listCategoriesOutputPort).list(Mockito.any());
    }
}
