package com.fiap.tc.core.usecase.product;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.core.application.usecase.product.RegisterProductUseCase;
import com.fiap.tc.core.domain.entities.Product;
import com.fiap.tc.adapters.driver.presentation.requests.ProductRequest;
import com.fiap.tc.core.application.ports.out.product.RegisterProductOutputPort;
import com.fiap.tc.fixture.FixtureTest;
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
public class RegisterProductUseCaseTest extends FixtureTest {

    @Mock
    private RegisterProductOutputPort registerProductOutputPort;

    @InjectMocks
    private RegisterProductUseCase registerProductUseCase;

    private ProductRequest request;

    private Product product;

    @BeforeEach
    public void setUp() {
        request = Fixture.from(ProductRequest.class).gimme("valid");
        product = Fixture.from(Product.class).gimme("valid");
    }

    @Test
    public void registerProductTest() {
        when(registerProductOutputPort.saveOrUpdate(Mockito.any())).thenReturn(product);
        var productResult = registerProductUseCase.register(product);

        Assertions.assertEquals(product, productResult);
        verify(registerProductOutputPort).saveOrUpdate(Mockito.any());
    }

}