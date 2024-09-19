package com.fiap.tc.core.application.usecase.ports.in.customer;

import com.fiap.tc.core.domain.entities.Customer;

public interface RegisterCustomerInputPort {
    Customer register(String document, String name, String email);
}
