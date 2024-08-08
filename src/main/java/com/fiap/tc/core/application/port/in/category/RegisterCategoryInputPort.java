package com.fiap.tc.core.application.port.in.category;

import com.fiap.tc.core.domain.model.Category;
import com.fiap.tc.adapters.driver.presentation.requests.CategoryRequest;

public interface RegisterCategoryInputPort {
    Category register(CategoryRequest categoryRequest);
}
