package com.fiap.tc.core.application.ports.in.category;

import java.util.UUID;

public interface DeleteCategoryInputPort {
    void delete(UUID uuid);
}
