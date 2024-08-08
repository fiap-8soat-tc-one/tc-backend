package com.fiap.tc.core.application.ports.out.category;

import com.fiap.tc.core.domain.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListCategoriesOutputPort {
    Page<Category> list(Pageable pageable);
}

