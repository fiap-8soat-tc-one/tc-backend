package com.fiap.tc.adapter.repository.output;

import com.fiap.tc.adapter.repository.CustomerRepository;
import com.fiap.tc.adapter.repository.entity.CustomerEntity;
import com.fiap.tc.adapter.repository.entity.embeddable.Audit;
import com.fiap.tc.core.domain.exception.NotFoundException;
import com.fiap.tc.core.domain.model.Customer;
import com.fiap.tc.core.port.out.DeleteCustomerOutputPort;
import com.fiap.tc.core.port.out.ListCustomersOutputPort;
import com.fiap.tc.core.port.out.LoadCustomerOutputPort;
import com.fiap.tc.core.port.out.SaveCustomerOutputPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.fiap.tc.adapter.repository.mapper.base.MapperConstants.CUSTOMER_MAPPER;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Repository
public class CustomerOutputAdapter implements SaveCustomerOutputPort, LoadCustomerOutputPort, ListCustomersOutputPort, DeleteCustomerOutputPort {
    private final CustomerRepository customerRepository;

    public CustomerOutputAdapter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void delete(String document) {
        var customer = customerRepository.findByDocument(document);

        if (nonNull(customer)) {
            customerRepository.delete(customer);
        }
    }

    @Override
    public Page<Customer> list(Pageable pageable) {

        var categories = customerRepository.findAll(pageable);
        return categories.map(CUSTOMER_MAPPER::fromEntity);
    }

    @Override
    public Customer load(String document) {
        var customer = customerRepository.findByDocument(document);

        if (isNull(customer)) {
            throw new NotFoundException(format("Customer with document %s not found!", document));
        }

        return CUSTOMER_MAPPER.fromEntity(customer);
    }

    @Override
    public Customer saveOrUpdate(String document, String name, String email) {
        var customer = customerRepository.findByDocument(document);

        if (isNull(customer)) {
            return CUSTOMER_MAPPER.fromEntity(customerRepository.save(buildCustomerEntity(document, name, email)));
        }
        var currentAudit = customer.getAudit();
        
        currentAudit.setUpdatedDate(LocalDateTime.now());
        
        customer.setName(name);
        customer.setEmail(email);
        customer.setAudit(currentAudit);
        
        return CUSTOMER_MAPPER.fromEntity(customerRepository.save(customer));
    }

    private CustomerEntity buildCustomerEntity(String document, String name, String email) {
        var newCustomer = new CustomerEntity();
        var audit = new Audit();
        audit.setActive(true);
        audit.setRegisterDate(LocalDateTime.now());
        audit.setUpdatedDate(LocalDateTime.now());
        newCustomer.setName(name);
        newCustomer.setEmail(email);
        newCustomer.setDocument(document);
        newCustomer.setUuid(UUID.randomUUID());
        newCustomer.setAudit(audit);
        return newCustomer;
    }

}
