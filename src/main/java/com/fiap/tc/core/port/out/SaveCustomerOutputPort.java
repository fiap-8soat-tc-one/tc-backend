package com.fiap.tc.core.port.out;

import com.fiap.tc.core.domain.model.Customer;

public interface SaveCustomerOutputPort {
    Customer save(String document, String name, String email);
}
