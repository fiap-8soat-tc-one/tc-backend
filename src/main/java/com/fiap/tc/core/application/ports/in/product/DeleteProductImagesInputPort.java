package com.fiap.tc.core.application.ports.in.product;

import com.fiap.tc.adapters.driver.presentation.requests.DeleteProductImagesRequest;

public interface DeleteProductImagesInputPort {
    void delete(DeleteProductImagesRequest request);
}
