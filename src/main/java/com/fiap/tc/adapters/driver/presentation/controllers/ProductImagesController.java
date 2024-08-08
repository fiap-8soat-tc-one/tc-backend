package com.fiap.tc.adapters.driver.presentation.controllers;

import com.fiap.tc.adapters.driver.presentation.URLMapping;
import com.fiap.tc.adapters.driver.presentation.response.DefaultResponse;
import com.fiap.tc.core.domain.model.Product;
import com.fiap.tc.adapters.driver.presentation.requests.DeleteProductImagesRequest;
import com.fiap.tc.adapters.driver.presentation.requests.RegisterProductImagesRequest;
import com.fiap.tc.core.application.port.in.product.DeleteProductImagesInputPort;
import com.fiap.tc.core.application.port.in.product.RegisterProductImagesInputPort;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = URLMapping.ROOT_PRIVATE_API_PRODUCTS_IMAGES)
@Api(tags = "Products Images API V1", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class ProductImagesController {

    private final RegisterProductImagesInputPort registerProductImagesInputPort;
    private final DeleteProductImagesInputPort deleteProductImagesInputPort;

    public ProductImagesController(RegisterProductImagesInputPort registerProductImagesInputPort,
                                   DeleteProductImagesInputPort deleteProductImagesInputPort) {
        this.registerProductImagesInputPort = registerProductImagesInputPort;
        this.deleteProductImagesInputPort = deleteProductImagesInputPort;
    }

    @ApiOperation(value = "upload product images", notes = "(Private Endpoint) This endpoint is responsible for upload product images. It is used on the administrative screen for managing categories and products.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully uploaded product images", response = Product.class),
            @ApiResponse(code = 401, message = "You are not authorized to perform this action"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('REGISTER_PRODUCTS')")
    public ResponseEntity<Product> upload(
            @ApiParam(value = "Product Image details for upload", required = true) @RequestBody @Valid RegisterProductImagesRequest request) {
        return ok(registerProductImagesInputPort.register(request));
    }

    @ApiOperation(value = "delete product images by ids", notes = "(Private Endpoint) This endpoint is responsible for removing images of a product. It is used on the administrative screen for managing categories and products.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted product images", response = DefaultResponse.class),
            @ApiResponse(code = 401, message = "You are not authorized to perform this action"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @DeleteMapping
    @PreAuthorize("hasAuthority('DELETE_PRODUCTS')")
    public ResponseEntity<DefaultResponse> delete(
            @ApiParam(value = "Product Image Ids for delete", required = true)
            @RequestBody @Valid DeleteProductImagesRequest request) {
        deleteProductImagesInputPort.delete(request);
        return ok(new DefaultResponse());
    }
}