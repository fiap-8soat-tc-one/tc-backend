package com.fiap.tc.core.application.usecase.product;

import com.fiap.tc.core.application.ports.out.product.DeleteProductOutputPort;
import com.fiap.tc.fixture.FixtureTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class DeleteProductUseCaseTest extends FixtureTest {

    public static final UUID ID_PRODUCT = UUID.randomUUID();
    @Mock
    private DeleteProductOutputPort deleteProductOutputPort;

    @InjectMocks
    private DeleteProductUseCase deleteProductUseCase;

    @Test
    public void deleteProductTest() {
        deleteProductUseCase.delete(ID_PRODUCT);
        Mockito.verify(deleteProductOutputPort).delete(ID_PRODUCT);
    }


}