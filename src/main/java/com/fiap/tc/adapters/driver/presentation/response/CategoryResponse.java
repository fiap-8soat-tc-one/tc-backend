package com.fiap.tc.adapters.driver.presentation.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class CategoryResponse {

    @ApiModelProperty(
            value = "Category Id",
            example = "7ba2a960-2354-466f-8868-6ad713742407",
            dataType = "UUID"
    )
    private UUID id;

    @NotEmpty
    @Size(max = 255, message = "Invalid category name")
    @ApiModelProperty(
            value = "Category name",
            required = true,
            example = "lanche",
            dataType = "String"
    )
    private String name;

    @NotEmpty
    @Size(max = 255, message = "Invalid category description")
    @ApiModelProperty(
            value = "Category description",
            required = true,
            example = "categoria de lanche",
            dataType = "String"
    )
    private String description;
}
