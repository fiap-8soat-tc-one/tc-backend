package com.fiap.tc.core.application.port.in.category;

import java.util.UUID;

public interface DeleteCategoryInputPort {
    void delete(UUID uuid);
}
