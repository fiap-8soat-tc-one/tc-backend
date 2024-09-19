package com.fiap.tc.core.application.usecase.ports.out.customer;

import com.fiap.tc.core.domain.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListCustomersOutputPort {
    Page<Customer> list(Pageable pageable);
}
