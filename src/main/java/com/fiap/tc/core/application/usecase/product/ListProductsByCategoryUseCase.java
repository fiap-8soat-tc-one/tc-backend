package com.fiap.tc.core.application.usecase.product;

import com.fiap.tc.core.application.ports.in.product.ListProductsByCategoryInputPort;
import com.fiap.tc.core.domain.entities.Product;
import com.fiap.tc.core.application.ports.out.product.ListProductsByCategoryOutputPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ListProductsByCategoryUseCase implements ListProductsByCategoryInputPort {

    private final ListProductsByCategoryOutputPort listProductsByCategoryOutputPort;

    public ListProductsByCategoryUseCase(ListProductsByCategoryOutputPort listProductsByCategoryOutputPort) {
        this.listProductsByCategoryOutputPort = listProductsByCategoryOutputPort;
    }

    @Override
    public Page<Product> list(UUID idCategory, Pageable pageable) {
        return listProductsByCategoryOutputPort.list(idCategory, pageable);
    }
}
