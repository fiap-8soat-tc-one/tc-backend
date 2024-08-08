package com.fiap.tc.adapters.driver.presentation.controllers;

import com.fiap.tc.adapters.driver.presentation.URLMapping;
import com.fiap.tc.adapters.driver.presentation.requests.ProductRequest;
import com.fiap.tc.adapters.driver.presentation.response.DefaultResponse;
import com.fiap.tc.adapters.driver.presentation.response.ProductResponse;
import com.fiap.tc.core.application.ports.in.product.*;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static com.fiap.tc.adapters.driver.presentation.mappers.base.MapperConstants.PRODUCT_MAPPER;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping
@Api(tags = "Products API V1", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class ProductController {

    private final RegisterProductInputPort registerProductInputPort;
    private final UpdateProductInputPort updateProductInputPort;
    private final LoadProductInputPort loadProductInputPort;
    private final ListProductsByCategoryInputPort listProductsByCategoryInputPort;
    private final DeleteProductInputPort deleteProductInputPort;

    public ProductController(RegisterProductInputPort registerProductInputPort,
                             UpdateProductInputPort updateProductInputPort,
                             LoadProductInputPort loadProductInputPort,
                             ListProductsByCategoryInputPort listProductsByCategoryInputPort,
                             DeleteProductInputPort deleteProductInputPort) {
        this.registerProductInputPort = registerProductInputPort;
        this.updateProductInputPort = updateProductInputPort;
        this.loadProductInputPort = loadProductInputPort;
        this.listProductsByCategoryInputPort = listProductsByCategoryInputPort;
        this.deleteProductInputPort = deleteProductInputPort;
    }

    @ApiOperation(value = "list of products by category", notes = "(Public Endpoint) This endpoint is responsible for listing the products by category registered in the snack bar's system.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list", response = ProductResponse.class, responseContainer = "Page"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @GetMapping(path = URLMapping.ROOT_PUBLIC_API_PRODUCTS + "/categories/{categoryId}")
    public ResponseEntity<Page<ProductResponse>> list(
            @ApiParam(value = "Category Id of the products to be retrieved", required = true) @PathVariable UUID categoryId,
            @ApiParam(required = true, value = "Pagination information") Pageable pageable) {
        return ok(listProductsByCategoryInputPort.list(categoryId, pageable).map(PRODUCT_MAPPER::fromDomain));
    }

    @ApiOperation(value = "create/update product", notes = "(Private Endpoint) This endpoint is responsible for creating or modifying a product. It is used on the administrative screen for managing categories and products.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully saved/updated product", response = ProductResponse.class),
            @ApiResponse(code = 401, message = "You are not authorized to perform this action"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @PostMapping(path = URLMapping.ROOT_PRIVATE_API_PRODUCTS, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('REGISTER_PRODUCTS')")
    public ResponseEntity<ProductResponse> saveOrUpdate(
            @ApiParam(value = "Product details for saving/updating", required = true) @RequestBody @Valid ProductRequest request) {
        return ok(PRODUCT_MAPPER.fromDomain(registerProductInputPort.register(request)));
    }

    @ApiOperation(value = "update product by id", notes = "(Private Endpoint) This endpoint is responsible for update a product. It is used on the administrative screen for managing categories and products.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated product", response = ProductResponse.class),
            @ApiResponse(code = 401, message = "You are not authorized to perform this action"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @PutMapping(path = URLMapping.ROOT_PRIVATE_API_PRODUCTS + "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EDIT_PRODUCTS')")
    public ResponseEntity<ProductResponse> update(
            @ApiParam(value = "ID of the product to be deleted", required = true) @PathVariable UUID id,
            @ApiParam(value = "Product details for saving/updating", required = true) @RequestBody @Valid ProductRequest request) {
        return ok(PRODUCT_MAPPER.fromDomain(updateProductInputPort.update(id, request)));
    }

    @ApiOperation(value = "get product by id", notes = "(Private Endpoint) This endpoint is responsible for fetching a product through its unique identifier. It is used on the administrative screen to assist in product creation.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved product", response = ProductResponse.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @GetMapping(path = URLMapping.ROOT_PRIVATE_API_PRODUCTS + "/{id}")
    @PreAuthorize("hasAuthority('SEARCH_PRODUCTS')")
    public ResponseEntity<ProductResponse> get(
            @ApiParam(value = "ID of the product to be retrieved", required = true) @PathVariable UUID id) {
        return ok(PRODUCT_MAPPER.fromDomain(loadProductInputPort.load(id)));
    }

    @ApiOperation(value = "delete product by id", notes = "(Private Endpoint) This endpoint is responsible for removing a product through its unique identifier. It is used on the administrative screen for managing categories and products.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted category", response = ProductResponse.class),
            @ApiResponse(code = 401, message = "You are not authorized to perform this action"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @DeleteMapping(path = URLMapping.ROOT_PRIVATE_API_PRODUCTS + "/{id}")
    @PreAuthorize("hasAuthority('DELETE_PRODUCTS')")
    public ResponseEntity<DefaultResponse> delete(
            @ApiParam(value = "ID of the product to be deleted", required = true) @PathVariable UUID id) {
        deleteProductInputPort.delete(id);
        return ok(new DefaultResponse());
    }
}