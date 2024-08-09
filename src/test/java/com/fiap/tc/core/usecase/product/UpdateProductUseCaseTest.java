//package com.fiap.tc.core.usecase.product;
//
//import br.com.six2six.fixturefactory.Fixture;
//import com.fiap.tc.core.application.usecase.product.UpdateProductUseCase;
//import com.fiap.tc.core.domain.entities.Product;
//import com.fiap.tc.adapters.driver.presentation.requests.ProductRequest;
//import com.fiap.tc.core.application.ports.out.product.UpdateProductOutputPort;
//import com.fiap.tc.fixture.FixtureTest;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.UUID;
//
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class UpdateProductUseCaseTest extends FixtureTest {
//
//    public static final UUID ID_PRODUCT = UUID.randomUUID();
//    @Mock
//    private UpdateProductOutputPort updateProductOutputPort;
//
//    @InjectMocks
//    private UpdateProductUseCase updateProductUseCase;
//
//    private Product product;
//
//    private ProductRequest request;
//
//    @BeforeEach
//    public void setUp() {
//        product = Fixture.from(Product.class).gimme("valid");
//        request = Fixture.from(ProductRequest.class).gimme("valid");
//    }
//
//    @Test
//    public void updateProductTest() {
//        when(updateProductOutputPort.update(Mockito.any())).thenReturn(product);
//        updateProductUseCase.update(ID_PRODUCT, request);
//        verify(updateProductOutputPort).update(Mockito.any());
//    }
//
//
//}