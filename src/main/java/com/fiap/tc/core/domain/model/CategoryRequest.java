package com.fiap.tc.core.domain.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CategoryRequest {

    @NotEmpty
    @ApiModelProperty(
            value = "Category name",
            required = true,
            example = "lanche",
            dataType = "String"
    )
    private String name;

    @NotEmpty
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
