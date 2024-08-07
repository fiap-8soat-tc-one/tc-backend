package com.fiap.tc.application.port.in.category;

import com.fiap.tc.core.domain.model.Category;

import java.util.UUID;

public interface LoadCategoryInputPort {
    Category load(UUID uuid);
}
