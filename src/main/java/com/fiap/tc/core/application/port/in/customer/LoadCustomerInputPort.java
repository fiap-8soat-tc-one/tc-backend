package com.fiap.tc.core.application.port.in.customer;

import com.fiap.tc.core.domain.model.Customer;

public interface LoadCustomerInputPort {
    Customer load(String document);
}
