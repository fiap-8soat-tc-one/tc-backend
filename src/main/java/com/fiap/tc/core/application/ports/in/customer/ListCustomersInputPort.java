package com.fiap.tc.core.application.ports.in.customer;

import com.fiap.tc.core.domain.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListCustomersInputPort {
    Page<Customer> list(Pageable pageable);
}
