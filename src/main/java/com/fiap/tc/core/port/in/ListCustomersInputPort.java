package com.fiap.tc.core.port.in;

import com.fiap.tc.core.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListCustomersInputPort {
    Page<Customer> list(Pageable pageable);
}
