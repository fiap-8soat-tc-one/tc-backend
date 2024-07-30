package com.fiap.tc.core.port.out.customer;

import com.fiap.tc.core.domain.model.Customer;

public interface SaveCustomerOutputPort {
    Customer saveOrUpdate(String document, String name, String email);
}
