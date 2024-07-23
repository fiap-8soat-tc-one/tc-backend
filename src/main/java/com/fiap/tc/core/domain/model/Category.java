package com.fiap.tc.core.domain.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class Category {

    @ApiModelProperty(
            value = "UUID Category",
            example = "123456789",
            dataType = "UUID"
    )
    private UUID uuid;

    @ApiModelProperty(
            value = "Category name",
            required = true,
            example = "lanche",
            dataType = "String"
    )
    private String name;

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
