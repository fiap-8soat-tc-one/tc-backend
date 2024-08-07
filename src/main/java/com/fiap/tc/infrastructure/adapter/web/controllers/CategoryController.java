package com.fiap.tc.infrastructure.adapter.web.controllers;

import com.fiap.tc.infrastructure.adapter.web.URLMapping;
import com.fiap.tc.infrastructure.adapter.web.responses.CategoryResponse;
import com.fiap.tc.infrastructure.adapter.web.responses.DefaultResponse;
import com.fiap.tc.core.domain.model.Category;
import com.fiap.tc.infrastructure.adapter.web.requests.CategoryRequest;
import com.fiap.tc.application.port.in.category.DeleteCategoryInputPort;
import com.fiap.tc.application.port.in.category.ListCategoriesInputPort;
import com.fiap.tc.application.port.in.category.LoadCategoryInputPort;
import com.fiap.tc.application.port.in.category.RegisterCategoryInputPort;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = URLMapping.ROOT_API_CATEGORIES)
@Api(tags = "Categories API V1", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class CategoryController {

    private final RegisterCategoryInputPort registerCategoryInputPort;
    private final LoadCategoryInputPort loadCategoryInputPort;
    private final DeleteCategoryInputPort deleteCategoryInputPort;
    private final ListCategoriesInputPort listCategoriesInputPort;

    public CategoryController(RegisterCategoryInputPort registerCategoryInputPort,
                              LoadCategoryInputPort loadCategoryInputPort, DeleteCategoryInputPort deleteCategoryInputPort,
                              ListCategoriesInputPort listCategoriesInputPort) {
        this.registerCategoryInputPort = registerCategoryInputPort;
        this.loadCategoryInputPort = loadCategoryInputPort;
        this.deleteCategoryInputPort = deleteCategoryInputPort;
        this.listCategoriesInputPort = listCategoriesInputPort;
    }

    @ApiOperation(value = "list of categories", notes = "(Private Endpoint) This endpoint is responsible for listing the categories registered in the snack bar's system. It is used on the administrative screen to assist in product creation.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list", response = CategoryResponse.class, responseContainer = "Page"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @GetMapping
    @PreAuthorize("hasAuthority('LIST_CATEGORIES')")
    public ResponseEntity<Page<Category>> list(
            @ApiParam(required = true, value = "Pagination information") Pageable pageable) {
        return ok(listCategoriesInputPort.list(pageable));
    }

    @ApiOperation(value = "create/update category", notes = "(Private Endpoint) This endpoint is responsible for creating or modifying a category. It is used on the administrative screen for managing categories and products.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully saved/updated category", response = CategoryResponse.class),
            @ApiResponse(code = 401, message = "You are not authorized to perform this action"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @PutMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('REGISTER_CATEGORIES')")
    public ResponseEntity<Category> saveOrUpdate(
            @ApiParam(value = "Category details for saving/updating", required = true) @RequestBody @Valid CategoryRequest category) {
        return ok(registerCategoryInputPort.register(category));
    }

    @ApiOperation(value = "get category by id", notes = "(Private Endpoint) This endpoint is responsible for fetching a category through its unique identifier. It is used on the administrative screen to assist in product creation.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved category", response = CategoryResponse.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('SEARCH_CATEGORIES')")
    public ResponseEntity<Category> get(
            @ApiParam(value = "ID of the category to be retrieved", required = true) @PathVariable UUID id) {
        return ok(loadCategoryInputPort.load(id));
    }

    @ApiOperation(value = "delete category by id", notes = "(Private Endpoint) This endpoint is responsible for removing a category through its unique identifier. It is used on the administrative screen for managing categories and products.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted category", response = CategoryResponse.class),
            @ApiResponse(code = 401, message = "You are not authorized to perform this action"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('DELETE_CATEGORIES')")
    public ResponseEntity<DefaultResponse> delete(
            @ApiParam(value = "ID of the category to be deleted", required = true) @PathVariable UUID id) {
        deleteCategoryInputPort.delete(id);
        return ok(new DefaultResponse());
    }
}