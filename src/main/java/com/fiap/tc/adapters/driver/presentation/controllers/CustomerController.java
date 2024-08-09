package com.fiap.tc.adapters.driver.presentation.controllers;

import com.fiap.tc.adapters.driver.presentation.URLMapping;
import com.fiap.tc.adapters.driver.presentation.requests.CustomerRequest;
import com.fiap.tc.adapters.driver.presentation.response.CustomerResponse;
import com.fiap.tc.core.application.ports.in.customer.DeleteCustomerInputPort;
import com.fiap.tc.core.application.ports.in.customer.ListCustomersInputPort;
import com.fiap.tc.core.application.ports.in.customer.LoadCustomerInputPort;
import com.fiap.tc.core.application.ports.in.customer.RegisterCustomerInputPort;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.fiap.tc.adapters.driver.presentation.mappers.base.MapperConstants.CUSTOMER_MAPPER;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping
@Api(tags = "Customer API V1", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE, authorizations = @Authorization(value = "JWT"))
public class CustomerController {

    private final RegisterCustomerInputPort registerCustomerInputPort;
    private final LoadCustomerInputPort loadCustomerInputPort;
    private final ListCustomersInputPort listCustomersInputPort;
    private final DeleteCustomerInputPort deleteCustomerInputPort;

    public CustomerController(
            RegisterCustomerInputPort registerCustomerInputPort,
            LoadCustomerInputPort loadCustomerInputPort,
            ListCustomersInputPort listCustomersInputPort,
            DeleteCustomerInputPort deleteCustomerInputPort) {
        this.registerCustomerInputPort = registerCustomerInputPort;
        this.loadCustomerInputPort = loadCustomerInputPort;
        this.listCustomersInputPort = listCustomersInputPort;
        this.deleteCustomerInputPort = deleteCustomerInputPort;
    }

    @ApiOperation(value = "list of customers", notes = "(Private Endpoint) This endpoint queries the entire customer database for potential promotional campaigns.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list", response = CustomerResponse.class, responseContainer = "Page"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @GetMapping(path = URLMapping.ROOT_PRIVATE_API_CUSTOMERS)
    @PreAuthorize("hasAuthority('LIST_CUSTOMERS')")
    public ResponseEntity<Page<CustomerResponse>> list(
            @ApiParam(required = true, value = "Pagination information") Pageable pageable) {
        return ok(listCustomersInputPort.list(pageable).map(CUSTOMER_MAPPER::fromDomain));
    }

    @ApiOperation(value = "create/update customer", notes = "(Public Endpoint) Customers are presented with a selection interface where they can choose to register using their name, email, and CPF. This endpoint is responsible for completing that registration.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully saved/updated customer", response = CustomerResponse.class),
    })
    @PutMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE, path = URLMapping.ROOT_PUBLIC_API_CUSTOMERS)
    public ResponseEntity<CustomerResponse> save(
            @ApiParam(value = "Customer details for saving/updating", required = true) @RequestBody @Valid CustomerRequest customerRequest) {
        return ok(CUSTOMER_MAPPER.fromDomain(registerCustomerInputPort.register(customerRequest.getDocument(), customerRequest.getName(), customerRequest.getEmail())));
    }

    @ApiOperation(value = "get customer by cpf", notes = "(Public Endpoint) Customers are presented with a selection interface where they can choose to register using their name, email, and CPF. This endpoint is responsible for completing the registration.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved customer", response = CustomerResponse.class),
    })
    @GetMapping(path = URLMapping.ROOT_PUBLIC_API_CUSTOMERS + "/{document}")
    public ResponseEntity<CustomerResponse> get(
            @ApiParam(value = "Document of the customer to be retrieved", required = true) @PathVariable String document) {
        return ok(CUSTOMER_MAPPER.fromDomain(loadCustomerInputPort.load(document)));
    }

    @ApiOperation(value = "delete customer by cpf", notes = "(Private Endpoint) This endpoint is responsible for removing a customer by their CPF.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted customer"),
            @ApiResponse(code = 401, message = "You are not authorized to perform this action"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @DeleteMapping(path = URLMapping.ROOT_PRIVATE_API_CUSTOMERS + "/{document}")
    @PreAuthorize("hasAuthority('DELETE_CUSTOMERS')")
    public ResponseEntity<Void> delete(
            @ApiParam(value = "Document of the customer to be deleted", required = true) @PathVariable String document) {
        deleteCustomerInputPort.delete(document);
        return noContent().build();
    }
}
