package com.fiap.tc.core.application.usecase.customer;

import com.fiap.tc.core.domain.entities.Customer;
import com.fiap.tc.core.application.usecase.ports.in.customer.ListCustomersInputPort;
import com.fiap.tc.core.application.usecase.ports.out.customer.ListCustomersOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ListCustomersUseCase implements ListCustomersInputPort {

    private final ListCustomersOutputPort listCustomersOutputPort;

    public ListCustomersUseCase(ListCustomersOutputPort listCustomersOutputPort) {
        this.listCustomersOutputPort = listCustomersOutputPort;
    }

    @Override
    public Page<Customer> list(Pageable pageable) {
        return listCustomersOutputPort.list(pageable);
    }
}
