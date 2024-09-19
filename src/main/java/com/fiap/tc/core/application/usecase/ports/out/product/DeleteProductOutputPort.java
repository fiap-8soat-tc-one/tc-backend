package com.fiap.tc.core.application.usecase.ports.out.product;

import java.util.UUID;

public interface DeleteProductOutputPort {
    void delete(UUID idProduct);
}

