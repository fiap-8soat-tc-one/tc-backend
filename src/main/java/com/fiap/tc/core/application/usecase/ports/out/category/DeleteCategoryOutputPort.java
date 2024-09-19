package com.fiap.tc.core.application.usecase.ports.out.category;

import java.util.UUID;

public interface DeleteCategoryOutputPort {
    void delete(UUID uuid);
}

