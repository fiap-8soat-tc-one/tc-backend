package com.fiap.tc.application.port.out.category;

import com.fiap.tc.core.domain.model.Category;

public interface SaveCategoryOutputPort {
    Category saveOrUpdate(String name, String description, boolean active);
}


