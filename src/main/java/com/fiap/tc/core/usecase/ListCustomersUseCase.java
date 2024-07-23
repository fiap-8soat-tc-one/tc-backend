package com.fiap.tc.core.usecase;

import com.fiap.tc.core.domain.model.Customer;
import com.fiap.tc.core.port.in.ListCustomersInputPort;
import com.fiap.tc.core.port.out.ListCustomersOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
