package com.fiap.tc.core.application.port.in.category;

import com.fiap.tc.core.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListCategoriesInputPort {
    Page<Category> list(Pageable pageable);
}
