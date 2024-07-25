package com.fiap.tc.core.port.out;

import com.fiap.tc.core.domain.model.Category;

import java.util.UUID;

public interface LoadCategoryOutputPort {
    Category load(UUID uuid);
}

