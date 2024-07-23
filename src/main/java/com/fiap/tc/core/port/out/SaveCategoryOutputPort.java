package com.fiap.tc.core.port.out;

import com.fiap.tc.core.domain.model.Category;

public interface SaveCategoryOutputPort {
    Category save(String name, String description, boolean active);
}
