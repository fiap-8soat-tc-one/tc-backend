package com.fiap.tc.adapter.web;

import com.fiap.tc.adapter.repository.entity.CustomerEntity;
import com.fiap.tc.core.domain.requests.CategoryRequest;
import com.fiap.tc.core.domain.model.Customer;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(path = URLMapping.ROOT_API_CUSTOMERS)
@Api(tags = "Customer API V1", produces = APPLICATION_JSON_VALUE)
public class CustomerController {

    @ApiOperation(value = "List Customers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List Categories", response = CustomerEntity.class)
    })
    @GetMapping
    public ResponseEntity<Page<Customer>> list(
            @ApiParam(required = true, value = "Authorization: Bearer <TOKEN>") @RequestHeader(value = "Authorization") String authorization,
            @ApiParam(required = true, value = "Categories Pagination") Pageable pageable) {
        return ok(null);
    }

    @ApiOperation(value = "Save/Update Customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Save Category", response = CustomerEntity.class)
    })
    @PutMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> save(
            @ApiParam(required = true, value = "Authorization: Bearer <TOKEN>") @RequestHeader(value = "Authorization") String authorization,
            @RequestBody @Valid CategoryRequest category) {
        
        return ok(null);
    }

    @ApiOperation(value = "Find Customer by Document")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Find Category", response = CustomerEntity.class)
    })
    @GetMapping(path = "/{document}")
    public ResponseEntity<Customer> get(
            @ApiParam(required = true, value = "Authorization: Bearer <TOKEN>") @RequestHeader(value = "Authorization") String authorization,
            @PathVariable String document) {

        return ok(null);
    }

    @ApiOperation(value = "Delete Customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Delete Category", response = CustomerEntity.class)
    })
    @DeleteMapping(path = "/{document}")
    public ResponseEntity<Void> delete(
            @ApiParam(required = true, value = "Authorization: Bearer <TOKEN>") @RequestHeader(value = "Authorization") String authorization,
            @PathVariable String document) {
        return noContent().build();
    }
}
