package com.fiap.tc.core.usecase;

import com.fiap.tc.core.domain.model.Category;
import com.fiap.tc.core.domain.model.Customer;
import com.fiap.tc.core.port.in.ListCategoriesInputPort;
import com.fiap.tc.core.port.in.ListCustomersInputPort;
import com.fiap.tc.core.port.out.ListCategoriesOutputPort;
import com.fiap.tc.core.port.out.ListCustomersOutputPort;
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


