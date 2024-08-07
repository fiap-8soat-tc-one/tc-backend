package com.fiap.tc.application.port.in.category;

import com.fiap.tc.core.domain.model.Category;
import com.fiap.tc.infrastructure.adapter.web.requests.CategoryRequest;

public interface RegisterCategoryInputPort {
    Category register(CategoryRequest categoryRequest);
}
