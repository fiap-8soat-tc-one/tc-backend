package com.fiap.tc.core.port.in.category;

import com.fiap.tc.core.domain.model.Category;
import com.fiap.tc.core.domain.requests.CategoryRequest;

import java.util.UUID;

public interface UpdateCategoryInputPort {
    Category update(UUID idCategory, CategoryRequest categoryRequest);
}
