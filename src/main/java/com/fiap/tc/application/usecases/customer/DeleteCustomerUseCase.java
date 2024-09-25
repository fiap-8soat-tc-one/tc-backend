package com.fiap.tc.application.usecases.customer;

import com.fiap.tc.application.gateways.CustomerGatewaySpec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeleteCustomerUseCase {

    private final CustomerGatewaySpec customerGateway;

    public DeleteCustomerUseCase(CustomerGatewaySpec customerGateway) {
        this.customerGateway = customerGateway;
    }

    public void delete(String document) {
        this.customerGateway.delete(document);
    }
}
