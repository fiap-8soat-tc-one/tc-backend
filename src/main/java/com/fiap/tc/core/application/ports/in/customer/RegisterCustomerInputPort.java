package com.fiap.tc.core.application.ports.in.customer;

import com.fiap.tc.core.domain.entities.Customer;
import com.fiap.tc.adapters.driver.presentation.requests.CustomerRequest;

public interface RegisterCustomerInputPort {
    Customer register(CustomerRequest customerRequest);
}
