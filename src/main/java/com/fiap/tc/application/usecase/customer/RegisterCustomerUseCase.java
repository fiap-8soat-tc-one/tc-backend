package com.fiap.tc.core.usecase.customer;

import com.fiap.tc.core.domain.model.Customer;
import com.fiap.tc.infrastructure.adapter.web.requests.CustomerRequest;
import com.fiap.tc.application.port.in.customer.RegisterCustomerInputPort;
import com.fiap.tc.application.port.out.customer.SaveCustomerOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegisterCustomerUseCase implements RegisterCustomerInputPort {

    private final SaveCustomerOutputPort saveCustomerOutputPort;

    public RegisterCustomerUseCase(SaveCustomerOutputPort saveCustomerOutputPort) {
        this.saveCustomerOutputPort = saveCustomerOutputPort;
    }

    @Override
    public Customer register(CustomerRequest customerRequest) {
        return saveCustomerOutputPort.saveOrUpdate(customerRequest.getDocument(), customerRequest.getName(), customerRequest.getEmail());
    }
}