package com.fiap.tc.infrastructure.presentation.controllers;

import com.fiap.tc.application.usecases.category.*;
import com.fiap.tc.infrastructure.presentation.URLMapping;
import com.fiap.tc.infrastructure.presentation.requests.CategoryRequest;
import com.fiap.tc.infrastructure.presentation.response.CategoryResponse;
import com.fiap.tc.infrastructure.presentation.response.DefaultResponse;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static com.fiap.tc.infrastructure.presentation.mappers.base.MapperConstants.CATEGORY_MAPPER;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = URLMapping.ROOT_API_CATEGORIES)
@Api(tags = "Categories API V1", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class CategoryController {

    private final RegisterCategoryUseCase registerCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final LoadCategoryUseCase loadCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final ListCategoriesUseCase listCategoriesUseCase;

    public CategoryController(RegisterCategoryUseCase registerCategoryUseCase,
                              UpdateCategoryUseCase updateCategoryUseCase,
                              LoadCategoryUseCase loadCategoryUseCase,
                              DeleteCategoryUseCase deleteCategoryUseCase,
                              ListCategoriesUseCase listCategoriesUseCase) {
        this.registerCategoryUseCase = registerCategoryUseCase;
        this.updateCategoryUseCase = updateCategoryUseCase;
        this.loadCategoryUseCase = loadCategoryUseCase;
        this.deleteCategoryUseCase = deleteCategoryUseCase;
        this.listCategoriesUseCase = listCategoriesUseCase;
    }

    @ApiOperation(value = "list of categories", notes = "(Private Endpoint) This endpoint is responsible for listing the categories registered in the snack bar's system. It is used on the administrative screen to assist in product creation.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list", response = CategoryResponse.class, responseContainer = "Page"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @GetMapping
    @PreAuthorize("hasAuthority('LIST_CATEGORIES')")
    public ResponseEntity<Page<CategoryResponse>> list(
            @ApiParam(required = true, value = "Pagination information") Pageable pageable) {
        return ok(listCategoriesUseCase.list(pageable).map(CATEGORY_MAPPER::fromDomain));
    }

    @ApiOperation(value = "create/update category", notes = "(Private Endpoint) This endpoint is responsible for creating or modifying a category. It is used on the administrative screen for managing categories and products.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully saved/updated category", response = CategoryResponse.class),
            @ApiResponse(code = 401, message = "You are not authorized to perform this action"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('REGISTER_CATEGORIES')")
    public ResponseEntity<CategoryResponse> saveOrUpdate(
            @ApiParam(value = "Category details for saving/updating", required = true) @RequestBody @Valid CategoryRequest category) {
        return ok(CATEGORY_MAPPER.fromDomain(registerCategoryUseCase.register(category.getName(),
                category.getDescription())));
    }

    @ApiOperation(value = "update category", notes = "(Private Endpoint) This endpoint is responsible for update a category. It is used on the administrative screen for managing categories and products.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated category", response = CategoryResponse.class),
            @ApiResponse(code = 401, message = "You are not authorized to perform this action"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('EDIT_CATEGORIES')")
    public ResponseEntity<CategoryResponse> update(
            @ApiParam(value = "ID of the category to be retrieved", required = true) @PathVariable UUID id,
            @ApiParam(value = "Category details for updating", required = true) @RequestBody @Valid CategoryRequest request) {
        return ok(CATEGORY_MAPPER.fromDomain(updateCategoryUseCase.update(id, request.getName(),
                request.getDescription())));
    }

    @ApiOperation(value = "get category by id", notes = "(Private Endpoint) This endpoint is responsible for fetching a category through its unique identifier. It is used on the administrative screen to assist in product creation.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved category", response = CategoryResponse.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('SEARCH_CATEGORIES')")
    public ResponseEntity<CategoryResponse> get(
            @ApiParam(value = "ID of the category to be retrieved", required = true) @PathVariable UUID id) {
        return ok(CATEGORY_MAPPER.fromDomain(loadCategoryUseCase.load(id)));
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
        deleteCategoryUseCase.delete(id);
        return ok(new DefaultResponse());
    }
}