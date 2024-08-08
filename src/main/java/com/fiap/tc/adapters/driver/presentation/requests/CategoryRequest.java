package com.fiap.tc.core.domain.requests;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CategoryRequest {

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
    @ApiModelProperty(
            value = "active status",
            example = "true",
            dataType = "Boolean"
    )
    private Boolean active = true;
}
