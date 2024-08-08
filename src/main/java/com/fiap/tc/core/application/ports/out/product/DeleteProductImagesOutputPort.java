package com.fiap.tc.core.application.ports.out.product;

import java.util.List;
import java.util.UUID;

public interface DeleteProductImagesOutputPort {
    void delete(UUID idProduct, List<UUID> productImagesWithIds);
}
