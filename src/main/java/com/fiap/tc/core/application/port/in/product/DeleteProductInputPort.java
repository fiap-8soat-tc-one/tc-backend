package com.fiap.tc.core.application.port.in.product;

import java.util.UUID;

public interface DeleteProductInputPort {
    void delete(UUID idProduct);
}
