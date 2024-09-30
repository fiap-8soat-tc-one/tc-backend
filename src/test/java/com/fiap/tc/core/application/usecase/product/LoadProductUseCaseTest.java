package com.fiap.tc.core.application.usecase.product;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.application.gateways.ProductGatewaySpec;
import com.fiap.tc.application.usecases.product.LoadProductUseCase;
import com.fiap.tc.domain.entities.Product;
import com.fiap.tc.fixture.FixtureTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoadProductUseCaseTest extends FixtureTest {

    public static final UUID ID_PRODUCT = UUID.randomUUID();

    @Mock
    private ProductGatewaySpec productGateway;

    @InjectMocks
    private LoadProductUseCase loadProductUseCase;

    private Product product;

    @BeforeEach
    public void setUp() {
        product = Fixture.from(Product.class).gimme("valid");
    }

    @Test
    public void loadProductTest() {
        when(productGateway.load(ID_PRODUCT)).thenReturn(product);

        var productResult = loadProductUseCase.load(ID_PRODUCT);
        Assertions.assertEquals(product, productResult);
        verify(productGateway).load(ID_PRODUCT);
    }

}