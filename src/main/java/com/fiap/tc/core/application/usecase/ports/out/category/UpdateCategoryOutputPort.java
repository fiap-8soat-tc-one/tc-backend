package com.fiap.tc.core.application.usecase.ports.out.category;

import com.fiap.tc.core.domain.entities.Category;

import java.util.UUID;

public interface UpdateCategoryOutputPort {
    Category update(UUID idCategory, String name, String description);
}


