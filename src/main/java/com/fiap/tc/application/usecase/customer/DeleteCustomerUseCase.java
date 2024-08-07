package com.fiap.tc.core.usecase.customer;

import com.fiap.tc.application.port.in.customer.DeleteCustomerInputPort;
import com.fiap.tc.application.port.out.customer.DeleteCustomerOutputPort;
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
