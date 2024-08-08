package com.fiap.tc.core.application.port.out.customer;

import com.fiap.tc.core.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListCustomersOutputPort {
    Page<Customer> list(Pageable pageable);
}
