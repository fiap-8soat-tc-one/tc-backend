package com.fiap.tc.core.application.usecase.ports.in.category;

import com.fiap.tc.core.domain.entities.Category;

public interface RegisterCategoryInputPort {
    Category register(String name, String description);
}
