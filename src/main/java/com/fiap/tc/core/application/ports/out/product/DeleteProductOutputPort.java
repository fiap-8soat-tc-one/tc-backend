package com.fiap.tc.core.application.ports.out.product;

import java.util.UUID;

public interface DeleteProductOutputPort {
    void delete(UUID idProduct);
}

