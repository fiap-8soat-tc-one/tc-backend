package com.fiap.tc.core.port.in.product;

import com.fiap.tc.core.domain.requests.DeleteProductImagesRequest;

public interface DeleteProductImagesInputPort {
    void delete(DeleteProductImagesRequest request);
}
