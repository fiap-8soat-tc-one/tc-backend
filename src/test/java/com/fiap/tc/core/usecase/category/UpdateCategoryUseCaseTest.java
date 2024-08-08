package com.fiap.tc.core.usecase.category;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.core.application.usecase.category.UpdateCategoryUseCase;
import com.fiap.tc.core.domain.model.Category;
import com.fiap.tc.core.domain.requests.CategoryRequest;
import com.fiap.tc.core.application.port.out.category.UpdateCategoryOutputPort;
import com.fiap.tc.fixture.FixtureTest;
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
    private UpdateCategoryOutputPort updateCategoryOutputPort;

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
        when(updateCategoryOutputPort.update(ID_CATEGORY, request.getName(),
                request.getDescription(), request.getActive())).thenReturn(category);

        var categoryResult = updateCategoryUseCase.update(ID_CATEGORY, request);

        assertEquals(category, categoryResult);
    }


}