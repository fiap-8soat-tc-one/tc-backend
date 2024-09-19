package com.fiap.tc.core.application.usecase.customer;

import com.fiap.tc.core.application.usecase.ports.in.customer.DeleteCustomerInputPort;
import com.fiap.tc.core.application.usecase.ports.out.customer.DeleteCustomerOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeleteCustomerUseCase implements DeleteCustomerInputPort {

    private final DeleteCustomerOutputPort deleteCustomerOutputPort;

    public DeleteCustomerUseCase(DeleteCustomerOutputPort deleteCustomerOutputPort) {
        this.deleteCustomerOutputPort = deleteCustomerOutputPort;
    }


    @Override
    public void delete(String document) {
        this.deleteCustomerOutputPort.delete(document);
    }
}
