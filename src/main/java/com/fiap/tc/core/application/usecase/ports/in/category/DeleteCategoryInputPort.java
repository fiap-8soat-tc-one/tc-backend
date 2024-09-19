package com.fiap.tc.core.application.usecase.ports.in.category;

import java.util.UUID;

public interface DeleteCategoryInputPort {
    void delete(UUID uuid);
}
