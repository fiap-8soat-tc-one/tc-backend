package com.fiap.tc.application.port.in.customer;

import com.fiap.tc.core.domain.model.Customer;

public interface LoadCustomerInputPort {
    Customer load(String document);
}
