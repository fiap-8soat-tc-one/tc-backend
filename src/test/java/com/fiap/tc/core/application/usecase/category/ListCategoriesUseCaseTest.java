package com.fiap.tc.core.application.usecase.category;

import com.fiap.tc.application.gateways.CategoryGatewaySpec;
import com.fiap.tc.application.usecases.category.ListCategoriesUseCase;
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
    private CategoryGatewaySpec categoryGateway;

    @InjectMocks
    private ListCategoriesUseCase listCategoriesUseCase;

    @Test
    public void listCategoriesTest() {
        listCategoriesUseCase.list(Mockito.any());
        verify(categoryGateway).list(Mockito.any());
    }
}
