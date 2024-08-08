package com.fiap.tc.core.application.port.in.customer;

import com.fiap.tc.core.domain.model.Customer;
import com.fiap.tc.core.domain.requests.CustomerRequest;

public interface RegisterCustomerInputPort {
    Customer register(CustomerRequest customerRequest);
}
