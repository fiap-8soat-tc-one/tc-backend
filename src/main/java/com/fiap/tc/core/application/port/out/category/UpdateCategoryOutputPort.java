package com.fiap.tc.core.application.port.out.category;

import com.fiap.tc.core.domain.model.Category;

import java.util.UUID;

public interface UpdateCategoryOutputPort {
    Category update(UUID idCategory, String name, String description, boolean active);
}


