package com.fiap.tc.core.application.ports.in.product;

import java.util.List;
import java.util.UUID;

public interface DeleteProductImagesInputPort {
    void delete(UUID idProduct, List<UUID> productImagesWithIds);
}
