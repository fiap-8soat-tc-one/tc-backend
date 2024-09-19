package com.fiap.tc.core.application.usecase.ports.out.category;

import com.fiap.tc.core.domain.entities.Category;

import java.util.UUID;

public interface LoadCategoryOutputPort {
    Category load(UUID uuid);
}

