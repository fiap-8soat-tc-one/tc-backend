package com.fiap.tc.core.application.usecase.product;

import com.fiap.tc.application.gateways.ProductGatewaySpec;
import com.fiap.tc.application.usecases.product.DeleteProductUseCase;
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
    private ProductGatewaySpec productGateway;

    @InjectMocks
    private DeleteProductUseCase deleteProductUseCase;

    @Test
    public void deleteProductTest() {
        deleteProductUseCase.delete(ID_PRODUCT);
        Mockito.verify(productGateway).delete(ID_PRODUCT);
    }


}