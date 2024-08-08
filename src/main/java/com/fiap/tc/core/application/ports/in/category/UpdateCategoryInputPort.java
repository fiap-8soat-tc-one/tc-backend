package com.fiap.tc.core.application.ports.in.category;

import com.fiap.tc.core.domain.entities.Category;
import com.fiap.tc.adapters.driver.presentation.requests.CategoryRequest;

import java.util.UUID;

public interface UpdateCategoryInputPort {
    Category update(UUID idCategory, CategoryRequest categoryRequest);
}
