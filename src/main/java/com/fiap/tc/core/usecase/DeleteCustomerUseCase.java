package com.fiap.tc.core.usecase;

import com.fiap.tc.core.port.in.DeleteCustomerInputPort;
import com.fiap.tc.core.port.out.DeleteCustomerOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeleteCustomerUseCase implements DeleteCustomerInputPort {

    private final DeleteCustomerOutputPort  deleteCustomerOutputPort;

    public DeleteCustomerUseCase(DeleteCustomerOutputPort deleteCustomerOutputPort) {
        this.deleteCustomerOutputPort = deleteCustomerOutputPort;
    }


    @Override
    public void delete(String document) {
        this.deleteCustomerOutputPort.delete(document);
    }
}
