package com.fiap.tc.core.application.usecase.product;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.application.gateways.ProductImagesGatewaySpec;
import com.fiap.tc.application.usecases.product.DeleteProductImagesUseCase;
import com.fiap.tc.fixture.FixtureTest;
import com.fiap.tc.infrastructure.presentation.requests.DeleteProductImagesRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeleteProductImagesUseCaseTest extends FixtureTest {

    @Mock
    private ProductImagesGatewaySpec productImagesGateway;

    @InjectMocks
    private DeleteProductImagesUseCase deleteProductImagesUseCase;

    private DeleteProductImagesRequest request;

    @BeforeEach
    public void setUp() {
        request = Fixture.from(DeleteProductImagesRequest.class).gimme("valid");
    }

    @Test
    public void deleteProductImagesTest() {
        deleteProductImagesUseCase.delete(request.getIdProduct(), request.getImages());
        Mockito.verify(productImagesGateway).delete(request.getIdProduct(), request.getImages());
    }

}