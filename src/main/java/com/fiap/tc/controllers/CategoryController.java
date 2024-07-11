package com.fiap.tc.controllers;

import com.fiap.tc.controllers.response.DefaultResponse;
import com.fiap.tc.domains.products.Category;
import com.fiap.tc.dto.CategoryDto;
import com.fiap.tc.usecase.CategoryUseCase;
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

    private final CategoryUseCase categoryUseCase;

    public CategoryController(CategoryUseCase categoryUseCase) {
        this.categoryUseCase = categoryUseCase;
    }

    @ApiOperation(value = "List Categories")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List Categories", response = Category.class)
    })
    @GetMapping
    public ResponseEntity<Page<CategoryDto>> list(
            @ApiParam(required = true, value = "Authorization: Bearer <TOKEN>") @RequestHeader(value = "Authorization") String authorization,
            @ApiParam(required = true, value = "Categories Pagination") Pageable pageable) {
        return ok(categoryUseCase.list(pageable));
    }

    @ApiOperation(value = "Save/Update Category")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Save Category", response = Category.class)
    })
    @PutMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> save(
            @ApiParam(required = true, value = "Authorization: Bearer <TOKEN>") @RequestHeader(value = "Authorization") String authorization,
            @RequestBody @Valid CategoryDto category) {
        return ok(categoryUseCase.save(category));
    }

    @ApiOperation(value = "Find Category")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Find Category", response = Category.class)
    })
    @GetMapping(path = "/{uuid}")
    public ResponseEntity<CategoryDto> get(
            @ApiParam(required = true, value = "Authorization: Bearer <TOKEN>") @RequestHeader(value = "Authorization") String authorization,
            @PathVariable UUID uuid) {
        return ok(categoryUseCase.find(uuid));
    }

    @ApiOperation(value = "Delete Category")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Delete Category", response = Category.class)
    })
    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<DefaultResponse> delete(
            @ApiParam(required = true, value = "Authorization: Bearer <TOKEN>") @RequestHeader(value = "Authorization") String authorization,
            @PathVariable UUID uuid) {
        categoryUseCase.delete(uuid);
        return ok(new DefaultResponse());
    }
}
