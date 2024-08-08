package com.fiap.tc.core.port.in.product;

import java.util.UUID;

public interface DeleteProductInputPort {
    void delete(UUID idProduct);
}
