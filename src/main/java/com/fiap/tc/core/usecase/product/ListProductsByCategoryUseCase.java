package com.fiap.tc.core.usecase.product;

import com.fiap.tc.core.domain.model.Product;
import com.fiap.tc.core.port.in.product.ListProductsByCategoryInputPort;
import com.fiap.tc.core.port.out.product.ListProductsByCategoryOutputPort;
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
