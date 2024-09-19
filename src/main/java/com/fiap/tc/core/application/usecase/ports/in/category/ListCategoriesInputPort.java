package com.fiap.tc.core.application.usecase.ports.in.category;

import com.fiap.tc.core.domain.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListCategoriesInputPort {
    Page<Category> list(Pageable pageable);
}
