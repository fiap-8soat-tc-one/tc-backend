package com.fiap.tc.adapter.repository.output;

import com.fiap.tc.core.domain.model.Product;
import com.fiap.tc.core.domain.model.ProductImage;
import com.fiap.tc.core.port.out.product.DeleteProductImagesOutputPort;
import com.fiap.tc.core.port.out.product.RegisterProductImagesOutputPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductImagesOutputAdapter implements RegisterProductImagesOutputPort, DeleteProductImagesOutputPort {
    @Override
    public Product delete(UUID idProduct, List<UUID> productImagesWithIds) {
        return null;
    }

    @Override
    public Product save(UUID idProduct, List<ProductImage> images) {
        return null;
    }
}
