package com.fiap.tc.core.port.out.product;

import java.util.UUID;

public interface DeleteProductOutputPort {
    void delete(UUID idProduct);
}

