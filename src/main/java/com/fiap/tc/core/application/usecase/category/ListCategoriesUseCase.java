package com.fiap.tc.core.application.usecase.category;

import com.fiap.tc.core.application.ports.in.category.ListCategoriesInputPort;
import com.fiap.tc.core.domain.entities.Category;
import com.fiap.tc.core.application.ports.out.category.ListCategoriesOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ListCategoriesUseCase implements ListCategoriesInputPort {

    private final ListCategoriesOutputPort listCategoriesOutputPort;

    public ListCategoriesUseCase(ListCategoriesOutputPort listCategoriesOutputPort) {
        this.listCategoriesOutputPort = listCategoriesOutputPort;
    }

    @Override
    public Page<Category> list(Pageable pageable) {
        return listCategoriesOutputPort.list(pageable);
    }
}


