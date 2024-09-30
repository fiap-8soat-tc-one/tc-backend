package com.fiap.tc.application.usecases.customer;

import com.fiap.tc.application.gateways.CustomerGatewaySpec;
import com.fiap.tc.domain.entities.Customer;
import org.springframework.stereotype.Service;

@Service
public class RegisterCustomerUseCase {

    private final CustomerGatewaySpec customerGateway;

    public RegisterCustomerUseCase(CustomerGatewaySpec customerGateway) {
        this.customerGateway = customerGateway;
    }

    public Customer register(String document, String name, String email) {
        return customerGateway.saveOrUpdate(document, name, email);
    }
}