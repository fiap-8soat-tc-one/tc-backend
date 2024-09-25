package com.fiap.tc.core.application.usecase.category;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.application.gateways.CategoryGatewaySpec;
import com.fiap.tc.application.usecases.category.LoadCategoryUseCase;
import com.fiap.tc.domain.entities.Category;
import com.fiap.tc.fixture.FixtureTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoadCategoryUseCaseTest extends FixtureTest {

    @Mock
    private CategoryGatewaySpec categoryGateway;

    @InjectMocks
    private LoadCategoryUseCase loadCategoryUseCase;

    private Category category;

    @BeforeEach
    public void setUp() {
        category = Fixture.from(Category.class).gimme("valid");
    }


    @Test
    public void loadCategoryTest() {
        when(categoryGateway.load(Mockito.any())).thenReturn(category);

        var categoryResult = loadCategoryUseCase.load(UUID.randomUUID());
        Assertions.assertEquals(category, categoryResult);

    }


}