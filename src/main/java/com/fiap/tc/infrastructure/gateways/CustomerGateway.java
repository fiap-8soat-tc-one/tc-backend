package com.fiap.tc.infrastructure.gateways;

import com.fiap.tc.application.gateways.CustomerGatewaySpec;
import com.fiap.tc.domain.entities.Customer;
import com.fiap.tc.domain.exceptions.NotFoundException;
import com.fiap.tc.infrastructure.persistence.entities.CustomerEntity;
import com.fiap.tc.infrastructure.persistence.entities.embeddable.Audit;
import com.fiap.tc.infrastructure.persistence.repositories.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.fiap.tc.infrastructure.gateways.mappers.base.MapperConstants.CUSTOMER_MAPPER;
import static java.lang.String.format;

@Service
public class CustomerGateway implements CustomerGatewaySpec {
    private final CustomerRepository repository;

    public CustomerGateway(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer load(String document) {
        var customerEntityOptional = repository.findByDocument(document);

        if (customerEntityOptional.isEmpty()) {
            throw new NotFoundException(format("Customer with document %s not found!", document));
        }

        return CUSTOMER_MAPPER.fromEntity(customerEntityOptional.get());
    }

    @Override
    public void delete(String document) {
        var customerEntityOptional = repository.findByDocument(document);
        customerEntityOptional.ifPresent(repository::delete);
    }

    @Override
    public Customer saveOrUpdate(String document, String name, String email) {
        var customerEntityOptional = repository.findByDocument(document);

        if (customerEntityOptional.isPresent()) {
            var customerEntity = customerEntityOptional.get();
            var currentAudit = customerEntity.getAudit();
            currentAudit.setUpdatedDate(LocalDateTime.now());
            customerEntity.setName(name);
            customerEntity.setEmail(email);
            customerEntity.setAudit(currentAudit);

            return CUSTOMER_MAPPER.fromEntity(repository.save(customerEntity));
        }

        return CUSTOMER_MAPPER.fromEntity(repository.save(buildCustomerEntity(document, name, email)));
    }

    @Override
    public Page<Customer> list(Pageable pageable) {
        var categories = repository.findAll(pageable);
        return categories.map(CUSTOMER_MAPPER::fromEntity);
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
