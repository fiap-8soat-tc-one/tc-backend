package com.fiap.tc.core.usecase.product;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.core.application.usecase.product.RegisterProductImagesUseCase;
import com.fiap.tc.core.domain.model.Product;
import com.fiap.tc.core.domain.requests.RegisterProductImagesRequest;
import com.fiap.tc.core.application.port.out.product.RegisterProductImagesOutputPort;
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
public class RegisterProductImagesUseCaseTest extends FixtureTest {

    @Mock
    private RegisterProductImagesOutputPort registerProductImagesOutputPort;

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
        when(registerProductImagesOutputPort.save(Mockito.any(), Mockito.anyList())).thenReturn(product);

        var productResult = registerProductImagesUseCase.register(request);

        Assertions.assertEquals(product, productResult);
        verify(registerProductImagesOutputPort).save(Mockito.any(), Mockito.anyList());
    }


}