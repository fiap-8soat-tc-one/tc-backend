package com.fiap.tc.core.application.usecase.ports.in.category;

import com.fiap.tc.core.domain.entities.Category;

import java.util.UUID;

public interface LoadCategoryInputPort {
    Category load(UUID uuid);
}
