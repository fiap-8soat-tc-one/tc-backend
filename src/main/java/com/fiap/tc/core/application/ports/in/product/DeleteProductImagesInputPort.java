package com.fiap.tc.core.application.ports.in.product;

import com.fiap.tc.adapters.driver.presentation.requests.DeleteProductImagesRequest;

import java.util.List;
import java.util.UUID;

public interface DeleteProductImagesInputPort {
    void delete(UUID idProduct, List<UUID> productImagesWithIds);
}
