package com.fiap.tc.core.usecase.category;

import com.fiap.tc.application.port.out.category.DeleteCategoryOutputPort;
import com.fiap.tc.application.usecase.category.DeleteCategoryUseCase;
import com.fiap.tc.util.BaseTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class DeleteCategoryUseCaseTest extends BaseTest {

    @Mock
    private DeleteCategoryOutputPort deleteCategoryOutputPort;
    @InjectMocks
    private DeleteCategoryUseCase deleteCategoryUseCase;

    @Test
    public void deleteCategoryTest() {
        deleteCategoryUseCase.delete(UUID.randomUUID());
        Mockito.verify(deleteCategoryOutputPort).delete(Mockito.any());
    }

}