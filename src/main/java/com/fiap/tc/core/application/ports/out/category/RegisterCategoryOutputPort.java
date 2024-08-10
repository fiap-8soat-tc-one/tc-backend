package com.fiap.tc.core.application.ports.out.category;

import com.fiap.tc.core.domain.entities.Category;

public interface RegisterCategoryOutputPort {
    Category saveOrUpdate(String name, String description);
}


