package com.fiap.tc.core.application.usecase.category;

import com.fiap.tc.core.application.ports.out.category.ListCategoriesOutputPort;
import com.fiap.tc.fixture.FixtureTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ListCategoriesUseCaseTest extends FixtureTest {

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
