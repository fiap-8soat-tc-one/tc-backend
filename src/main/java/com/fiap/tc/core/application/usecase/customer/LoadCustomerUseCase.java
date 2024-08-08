package com.fiap.tc.core.application.usecase.customer;

import com.fiap.tc.core.domain.entities.Customer;
import com.fiap.tc.core.application.ports.in.customer.LoadCustomerInputPort;
import com.fiap.tc.core.application.ports.out.customer.LoadCustomerOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoadCustomerUseCase implements LoadCustomerInputPort {

    private final LoadCustomerOutputPort loadCustomerOutputPort;

    public LoadCustomerUseCase(LoadCustomerOutputPort loadCustomerOutputPort) {
        this.loadCustomerOutputPort = loadCustomerOutputPort;
    }


    @Override
    public Customer load(String document) {
        return loadCustomerOutputPort.load(document);
    }
}
