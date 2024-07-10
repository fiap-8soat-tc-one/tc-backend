package com.fiap.tc.controllers;

import com.fiap.tc.domains.products.Category;
import com.fiap.tc.repositories.CategoryRepository;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = URLMapping.ROOT_API_CATEGORIES)
@Api(tags = "Categories API V1", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @ApiOperation(value = "List Categories")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List Categories", response = Category.class)
    })
    @GetMapping
    public ResponseEntity<Page<Category>> list(
            @ApiParam(required = true, value = "Authorization: Bearer <TOKEN>") @RequestHeader(value = "Authorization") String authorization,
            @ApiParam(required = true, value = "Categories Pagination") Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return ok(categories);
    }
}
