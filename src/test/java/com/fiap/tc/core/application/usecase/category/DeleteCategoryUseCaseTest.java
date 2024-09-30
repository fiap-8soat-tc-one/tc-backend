package com.fiap.tc.core.application.usecase.category;

import com.fiap.tc.application.gateways.CategoryGatewaySpec;
import com.fiap.tc.application.usecases.category.DeleteCategoryUseCase;
import com.fiap.tc.fixture.FixtureTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class DeleteCategoryUseCaseTest extends FixtureTest {

    @Mock
    private CategoryGatewaySpec categoryGateway;
    @InjectMocks
    private DeleteCategoryUseCase deleteCategoryUseCase;

    @Test
    public void deleteCategoryTest() {
        deleteCategoryUseCase.delete(UUID.randomUUID());
        Mockito.verify(categoryGateway).delete(Mockito.any());
    }

}