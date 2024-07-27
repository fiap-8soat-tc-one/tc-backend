package com.fiap.tc.adapter.web;

import com.fiap.tc.adapter.repository.entity.CategoryEntity;
import com.fiap.tc.adapter.web.response.DefaultResponse;
import com.fiap.tc.core.domain.model.Category;
import com.fiap.tc.core.domain.requests.CategoryRequest;
import com.fiap.tc.core.port.in.category.DeleteCategoryInputPort;
import com.fiap.tc.core.port.in.category.ListCategoriesInputPort;
import com.fiap.tc.core.port.in.category.LoadCategoryInputPort;
import com.fiap.tc.core.port.in.category.RegisterCategoryInputPort;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = URLMapping.ROOT_API_CATEGORIES)
@Api(tags = "Categories API V1", produces = APPLICATION_JSON_VALUE)
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

    @ApiOperation(value = "List Categories")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List Categories", response = CategoryEntity.class)
    })
    @GetMapping
    public ResponseEntity<Page<Category>> list(
            @ApiParam(required = true, value = "Authorization: Bearer <TOKEN>") @RequestHeader(value = "Authorization") String authorization,
            @ApiParam(required = true, value = "Categories Pagination") Pageable pageable) {
        return ok(listCategoriesInputPort.list(pageable));
    }

    @ApiOperation(value = "Save/Update Category")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Save Category", response = CategoryEntity.class)
    })
    @PutMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> saveOrUpdate(
            @ApiParam(required = true, value = "Authorization: Bearer <TOKEN>") @RequestHeader(value = "Authorization") String authorization,
            @RequestBody @Valid CategoryRequest category) {
        return ok(registerCategoryInputPort.register(category));
    }

    @ApiOperation(value = "Find Category")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Find Category", response = CategoryEntity.class)
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<Category> get(
            @ApiParam(required = true, value = "Authorization: Bearer <TOKEN>") @RequestHeader(value = "Authorization") String authorization,
            @PathVariable UUID id) {
        return ok(loadCategoryInputPort.load(id));
    }

    @ApiOperation(value = "Delete Category")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Delete Category", response = CategoryEntity.class)
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<DefaultResponse> delete(
            @ApiParam(required = true, value = "Authorization: Bearer <TOKEN>") @RequestHeader(value = "Authorization") String authorization,
            @PathVariable UUID id) {
        deleteCategoryInputPort.delete(id);
        return ok(new DefaultResponse());
    }
}
