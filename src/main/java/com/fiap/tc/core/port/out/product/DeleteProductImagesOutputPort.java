package com.fiap.tc.core.port.out.product;

import java.util.List;
import java.util.UUID;

public interface DeleteProductImagesOutputPort {
    void delete(UUID idProduct, List<UUID> productImagesWithIds);
}
