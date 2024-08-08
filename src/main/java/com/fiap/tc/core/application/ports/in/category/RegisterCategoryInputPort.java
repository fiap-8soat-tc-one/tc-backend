package com.fiap.tc.core.application.ports.in.category;

import com.fiap.tc.core.domain.entities.Category;
import com.fiap.tc.adapters.driver.presentation.requests.CategoryRequest;

public interface RegisterCategoryInputPort {
    Category register(CategoryRequest categoryRequest);
}
