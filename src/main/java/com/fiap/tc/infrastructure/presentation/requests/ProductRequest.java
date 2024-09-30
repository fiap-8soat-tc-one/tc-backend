package com.fiap.tc.infrastructure.presentation.requests;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductRequest {

    @NotNull
    @ApiModelProperty(
            value = "Category Id",
            example = "7ba2a960-2354-466f-8868-6ad713742407",
            dataType = "UUID",
            required = true
    )
    private UUID idCategory;

    @NotEmpty
    @Size(max = 255, message = "Invalid product name")
    @ApiModelProperty(
            value = "Product name",
            required = true,
            example = "hamburger",
            dataType = "String"
    )
    private String name;

    @NotEmpty
    @Size(max = 255, message = "Invalid product description")
    @ApiModelProperty(
            value = "Product description",
            required = true,
            example = "hamburger 200g",
            dataType = "String"
    )
    private String description;

    @NotNull
    @ApiModelProperty(
            value = "Product price",
            example = "20.55",
            dataType = "BigDecimal",
            required = true
    )
    private BigDecimal price;

}
