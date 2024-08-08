package com.fiap.tc.core.application.ports.in.product;

import java.util.UUID;

public interface DeleteProductInputPort {
    void delete(UUID idProduct);
}
