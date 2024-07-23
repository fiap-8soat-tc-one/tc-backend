package com.fiap.tc.core.port.in;

import com.fiap.tc.core.domain.model.Customer;

public interface LoadCustomerInputPort {
    Customer load(String document);
}
