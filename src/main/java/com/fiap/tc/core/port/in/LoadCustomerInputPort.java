package com.fiap.tc.core.port.in;

import com.fiap.tc.core.domain.model.Category;
import com.fiap.tc.core.domain.model.Customer;

import java.util.UUID;

public interface LoadCustomerInputPort {
    Customer load(String document);
}
