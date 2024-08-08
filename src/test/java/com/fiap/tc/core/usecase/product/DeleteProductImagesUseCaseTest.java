package com.fiap.tc.core.usecase.product;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.core.application.usecase.product.DeleteProductImagesUseCase;
import com.fiap.tc.adapters.driver.presentation.requests.DeleteProductImagesRequest;
import com.fiap.tc.core.application.ports.out.product.DeleteProductImagesOutputPort;
import com.fiap.tc.fixture.FixtureTest;
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
    private DeleteProductImagesOutputPort deleteProductImagesOutputPort;

    @InjectMocks
    private DeleteProductImagesUseCase deleteProductImagesUseCase;

    private DeleteProductImagesRequest request;

    @BeforeEach
    public void setUp() {
        request = Fixture.from(DeleteProductImagesRequest.class).gimme("valid");
    }

    @Test
    public void deleteProductImagesTest() {
        deleteProductImagesUseCase.delete(request);
        Mockito.verify(deleteProductImagesOutputPort).delete(request.getIdProduct(), request.getImages());
    }


}