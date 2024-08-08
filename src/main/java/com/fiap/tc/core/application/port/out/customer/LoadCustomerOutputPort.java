package com.fiap.tc.core.port.out.customer;

import com.fiap.tc.core.domain.model.Customer;

public interface LoadCustomerOutputPort {
    Customer load(String document);
}
