package com.fiap.tc.core.application.usecase.category;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.application.gateways.CategoryGatewaySpec;
import com.fiap.tc.application.usecases.category.UpdateCategoryUseCase;
import com.fiap.tc.domain.entities.Category;
import com.fiap.tc.fixture.FixtureTest;
import com.fiap.tc.infrastructure.presentation.requests.CategoryRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateCategoryUseCaseTest extends FixtureTest {

    public static final UUID ID_CATEGORY = UUID.randomUUID();
    @Mock
    private CategoryGatewaySpec categoryGateway;

    @InjectMocks
    private UpdateCategoryUseCase updateCategoryUseCase;

    private CategoryRequest request;

    private Category category;

    @BeforeEach
    public void setUp() {
        request = Fixture.from(CategoryRequest.class).gimme("valid");
        category = Fixture.from(Category.class).gimme("valid");
    }

    @Test
    public void updateCategoryTest() {
        when(categoryGateway.update(ID_CATEGORY, request.getName(),
                request.getDescription())).thenReturn(category);

        var categoryResult = updateCategoryUseCase.update(ID_CATEGORY, request.getName(),
                request.getDescription());

        assertEquals(category, categoryResult);
    }


}