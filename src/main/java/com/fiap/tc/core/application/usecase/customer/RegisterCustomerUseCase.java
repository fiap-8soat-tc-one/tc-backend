package com.fiap.tc.core.application.usecase.customer;

import com.fiap.tc.core.application.usecase.ports.in.customer.RegisterCustomerInputPort;
import com.fiap.tc.core.application.usecase.ports.out.customer.SaveCustomerOutputPort;
import com.fiap.tc.core.domain.entities.Customer;
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
    public Customer register(String document, String name, String email) {
        return saveCustomerOutputPort.saveOrUpdate(document, name, email);
    }
}