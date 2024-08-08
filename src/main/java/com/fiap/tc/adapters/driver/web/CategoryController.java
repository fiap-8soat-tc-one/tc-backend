package com.fiap.tc.adapters.driver.web;

import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.CategoryEntity;
import com.fiap.tc.adapters.driver.web.response.DefaultResponse;
import com.fiap.tc.core.application.port.in.category.*;
import com.fiap.tc.core.domain.model.Category;
import com.fiap.tc.core.domain.requests.CategoryRequest;
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
    private final UpdateCategoryInputPort updateCategoryInputPort;
    private final LoadCategoryInputPort loadCategoryInputPort;
    private final DeleteCategoryInputPort deleteCategoryInputPort;
    private final ListCategoriesInputPort listCategoriesInputPort;

    public CategoryController(RegisterCategoryInputPort registerCategoryInputPort,
                              UpdateCategoryInputPort updateCategoryInputPort,
                              LoadCategoryInputPort loadCategoryInputPort,
                              DeleteCategoryInputPort deleteCategoryInputPort,
                              ListCategoriesInputPort listCategoriesInputPort) {
        this.registerCategoryInputPort = registerCategoryInputPort;
        this.updateCategoryInputPort = updateCategoryInputPort;
        this.loadCategoryInputPort = loadCategoryInputPort;
        this.deleteCategoryInputPort = deleteCategoryInputPort;
        this.listCategoriesInputPort = listCategoriesInputPort;
    }

    @ApiOperation(value = "list of categories", notes = "(Private Endpoint) This endpoint is responsible for listing the categories registered in the snack bar's system. It is used on the administrative screen to assist in product creation.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list", response = Category.class, responseContainer = "Page"),
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
            @ApiResponse(code = 200, message = "Successfully saved/updated category", response = Category.class),
            @ApiResponse(code = 401, message = "You are not authorized to perform this action"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('REGISTER_CATEGORIES')")
    public ResponseEntity<Category> saveOrUpdate(
            @ApiParam(value = "Category details for saving/updating", required = true) @RequestBody @Valid CategoryRequest category) {
        return ok(registerCategoryInputPort.register(category));
    }

    @ApiOperation(value = "update category", notes = "(Private Endpoint) This endpoint is responsible for update a category. It is used on the administrative screen for managing categories and products.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated category", response = Category.class),
            @ApiResponse(code = 401, message = "You are not authorized to perform this action"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EDIT_CATEGORIES')")
    public ResponseEntity<Category> update(
            @ApiParam(value = "ID of the category to be retrieved", required = true) @PathVariable UUID id,
            @ApiParam(value = "Category details for updating", required = true) @RequestBody @Valid CategoryRequest request) {
        return ok(updateCategoryInputPort.update(id, request));
    }

    @ApiOperation(value = "get category by id", notes = "(Private Endpoint) This endpoint is responsible for fetching a category through its unique identifier. It is used on the administrative screen to assist in product creation.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved category", response = CategoryEntity.class),
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
            @ApiResponse(code = 200, message = "Successfully deleted category", response = Category.class),
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