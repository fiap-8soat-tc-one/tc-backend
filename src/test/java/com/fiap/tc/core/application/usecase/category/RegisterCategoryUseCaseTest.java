package com.fiap.tc.core.application.usecase.category;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.application.gateways.CategoryGatewaySpec;
import com.fiap.tc.application.usecases.category.RegisterCategoryUseCase;
import com.fiap.tc.domain.entities.Category;
import com.fiap.tc.fixture.FixtureTest;
import com.fiap.tc.infrastructure.presentation.requests.CategoryRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegisterCategoryUseCaseTest extends FixtureTest {

    @Mock
    private CategoryGatewaySpec categoryGateway;

    @InjectMocks
    private RegisterCategoryUseCase registerCategoryUseCase;

    private Category category;
    private CategoryRequest categoryRequest;

    @BeforeEach
    public void setUp() {
        category = Fixture.from(Category.class).gimme("valid");
        categoryRequest = Fixture.from(CategoryRequest.class).gimme("valid");
    }

    @Test
    public void registerCategoryTest() {
        when(categoryGateway.register(categoryRequest.getName(), categoryRequest.getDescription())).thenReturn(category);

        var categoryResult = registerCategoryUseCase.register(categoryRequest.getName(), categoryRequest.getDescription());

        assertEquals(category, categoryResult);
        verify(categoryGateway).register(categoryRequest.getName(),
                categoryRequest.getDescription());

    }

}