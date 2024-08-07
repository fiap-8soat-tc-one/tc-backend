package com.fiap.tc.infrastructure.adapter.web.requests.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class CategoryResponse {

    @ApiModelProperty(
            value = "UUID Category",
            example = "7ba2a960-2354-466f-8868-6ad713742407",
            dataType = "UUID"
    )
    private UUID id;

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
