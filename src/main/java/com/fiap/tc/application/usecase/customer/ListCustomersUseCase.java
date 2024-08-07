package com.fiap.tc.application.usecase.customer;

import com.fiap.tc.core.domain.model.Customer;
import com.fiap.tc.application.port.in.customer.ListCustomersInputPort;
import com.fiap.tc.application.port.out.customer.ListCustomersOutputPort;
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
