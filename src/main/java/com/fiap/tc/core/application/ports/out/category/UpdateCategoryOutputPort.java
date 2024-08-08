package com.fiap.tc.core.application.ports.out.category;

import com.fiap.tc.core.domain.entities.Category;

import java.util.UUID;

public interface UpdateCategoryOutputPort {
    Category update(UUID idCategory, String name, String description, boolean active);
}


