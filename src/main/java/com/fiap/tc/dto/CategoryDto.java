package com.fiap.tc.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
public class CategoryDto {

    @ApiModelProperty(
            value = "UUID Category",
            example = "123456789",
            dataType = "UUID"
    )
    private UUID uuid;
    @NotEmpty
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
    private Boolean active;
}
