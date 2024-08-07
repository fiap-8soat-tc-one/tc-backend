package com.fiap.tc.application.port.out.category;

import com.fiap.tc.core.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListCategoriesOutputPort {
    Page<Category> list(Pageable pageable);
}

