package com.fiap.tc.adapters.driven.infrastructure.output;

import com.fiap.tc.adapters.driven.infrastructure.persistence.repositories.CustomerRepository;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.CustomerEntity;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.embeddable.Audit;
import com.fiap.tc.core.domain.exception.NotFoundException;
import com.fiap.tc.core.domain.model.Customer;
import com.fiap.tc.core.application.port.out.customer.DeleteCustomerOutputPort;
import com.fiap.tc.core.application.port.out.customer.ListCustomersOutputPort;
import com.fiap.tc.core.application.port.out.customer.LoadCustomerOutputPort;
import com.fiap.tc.core.application.port.out.customer.SaveCustomerOutputPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.fiap.tc.adapters.driven.infrastructure.persistence.mappers.base.MapperConstants.CUSTOMER_MAPPER;
import static java.lang.String.format;

@Service
public class CustomerOutputAdapter implements SaveCustomerOutputPort, LoadCustomerOutputPort, ListCustomersOutputPort, DeleteCustomerOutputPort {
    private final CustomerRepository customerRepository;

    public CustomerOutputAdapter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void delete(String document) {
        var customerEntityOptional = customerRepository.findByDocument(document);
        customerEntityOptional.ifPresent(customerRepository::delete);
    }

    @Override
    public Page<Customer> list(Pageable pageable) {
        var categories = customerRepository.findAll(pageable);
        return categories.map(CUSTOMER_MAPPER::fromEntity);
    }

    @Override
    public Customer load(String document) {
        var customerEntityOptional = customerRepository.findByDocument(document);

        if (customerEntityOptional.isEmpty()) {
            throw new NotFoundException(format("Customer with document %s not found!", document));
        }

        return CUSTOMER_MAPPER.fromEntity(customerEntityOptional.get());
    }

    @Override
    public Customer saveOrUpdate(String document, String name, String email) {
        var customerEntityOptional = customerRepository.findByDocument(document);

        if (customerEntityOptional.isPresent()) {
            var customerEntity = customerEntityOptional.get();
            var currentAudit = customerEntity.getAudit();
            currentAudit.setUpdatedDate(LocalDateTime.now());
            customerEntity.setName(name);
            customerEntity.setEmail(email);
            customerEntity.setAudit(currentAudit);

            return CUSTOMER_MAPPER.fromEntity(customerRepository.save(customerEntity));
        }

        return CUSTOMER_MAPPER.fromEntity(customerRepository.save(buildCustomerEntity(document, name, email)));
    }

    private CustomerEntity buildCustomerEntity(String document, String name, String email) {
        var newCustomer = new CustomerEntity();
        var audit = new Audit();
        audit.setActive(true);
        audit.setRegisterDate(LocalDateTime.now());
        newCustomer.setName(name);
        newCustomer.setEmail(email);
        newCustomer.setDocument(document);
        newCustomer.setUuid(UUID.randomUUID());
        newCustomer.setAudit(audit);
        return newCustomer;
    }

}
