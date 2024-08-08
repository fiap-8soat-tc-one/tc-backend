package com.fiap.tc.core.application.port.out.product;

import java.util.UUID;

public interface DeleteProductOutputPort {
    void delete(UUID idProduct);
}

