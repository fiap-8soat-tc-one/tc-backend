package com.fiap.tc.application.gateways;

import com.fiap.tc.domain.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CategoryGatewaySpec {

    Category load(UUID id);

    Category register(String name, String description);

    Category update(UUID id, String name, String description);

    Page<Category> list(Pageable pageable);

    void delete(UUID idCategory);
}
