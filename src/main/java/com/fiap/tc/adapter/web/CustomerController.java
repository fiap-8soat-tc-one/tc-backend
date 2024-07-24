package com.fiap.tc.adapter.web;

import com.fiap.tc.adapter.repository.entity.CustomerEntity;
import com.fiap.tc.core.domain.model.Customer;
import com.fiap.tc.core.domain.requests.CustomerRequest;
import com.fiap.tc.core.port.in.DeleteCustomerInputPort;
import com.fiap.tc.core.port.in.ListCustomersInputPort;
import com.fiap.tc.core.port.in.LoadCustomerInputPort;
import com.fiap.tc.core.port.in.RegisterCustomerInputPort;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = URLMapping.ROOT_PUBLIC_API_CUSTOMERS)
@Api(tags = "Customer API V1", produces = APPLICATION_JSON_VALUE)
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


    @ApiOperation(value = "List Customers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List Categories", response = CustomerEntity.class)
    })
    @GetMapping
    public ResponseEntity<Page<Customer>> list(
            @ApiParam(required = true, value = "Authorization: Bearer <TOKEN>") @RequestHeader(value = "Authorization") String authorization,
            @ApiParam(required = true, value = "Categories Pagination") Pageable pageable) {
        return ok(listCustomersInputPort.list(pageable));
    }

    @ApiOperation(value = "Save/Update Customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Save Category", response = CustomerEntity.class)
    })
    @PutMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> save(
            @RequestBody @Valid CustomerRequest customer) {

        return ok(registerCustomerInputPort.register(customer));
    }

    @ApiOperation(value = "Find Customer by Document")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Find Customer by Document", response = CustomerEntity.class)
    })
    @GetMapping(path = "/{document}")
    public ResponseEntity<Customer> get( 
            @PathVariable String document) {

        return ok(loadCustomerInputPort.load(document));
    }

    @ApiOperation(value = "Delete Customer")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Delete Customer by Document", response = CustomerEntity.class)
    })
    @DeleteMapping(path = "/{document}")
    public ResponseEntity<Void> delete(
            @ApiParam(required = true, value = "Authorization: Bearer <TOKEN>") @RequestHeader(value = "Authorization") String authorization,
            @PathVariable String document) {
        deleteCustomerInputPort.delete(document);
        return noContent().build();
    }
}
