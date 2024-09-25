package com.fiap.tc.core.application.usecase.product;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.application.gateways.ProductImagesGatewaySpec;
import com.fiap.tc.application.usecases.product.RegisterProductImagesUseCase;
import com.fiap.tc.domain.entities.Product;
import com.fiap.tc.fixture.FixtureTest;
import com.fiap.tc.infrastructure.presentation.requests.RegisterProductImagesRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegisterProductImagesUseCaseTest extends FixtureTest {

    @Mock
    private ProductImagesGatewaySpec productImagesGateway;

    @InjectMocks
    private RegisterProductImagesUseCase registerProductImagesUseCase;

    private Product product;

    private RegisterProductImagesRequest request;

    @BeforeEach
    public void setUp() {
        product = Fixture.from(Product.class).gimme("valid");
        request = Fixture.from(RegisterProductImagesRequest.class).gimme("valid");
    }

    @Test
    public void registerProductImagesTest() {
        when(productImagesGateway.register(Mockito.any(), Mockito.anyList())).thenReturn(product);

        var productResult = registerProductImagesUseCase.register(Mockito.any(), Mockito.anyList());

        Assertions.assertEquals(product, productResult);
        verify(productImagesGateway).register(Mockito.any(), Mockito.anyList());
    }


}