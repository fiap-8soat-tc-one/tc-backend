package com.fiap.tc.core.port.in.product;

import com.fiap.tc.core.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ListProductsByCategoryInputPort {
    Page<Product> list(UUID idCategory, Pageable pageable);
}
