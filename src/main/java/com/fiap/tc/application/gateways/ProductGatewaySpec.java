package com.fiap.tc.application.gateways;

import com.fiap.tc.domain.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductGatewaySpec {

    Product load(UUID id);

    Product register(Product product);

    Product update(Product product);

    Page<Product> list(UUID idCategory, Pageable pageable);

    void delete(UUID id);
}
