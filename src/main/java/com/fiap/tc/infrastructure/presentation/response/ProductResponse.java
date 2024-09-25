package com.fiap.tc.infrastructure.presentation.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class ProductResponse {

    @ApiModelProperty(
            value = "Product Id",
            example = "7ba2a960-2354-466f-8868-6ad713742407",
            dataType = "UUID"
    )
    private UUID id;

    @ApiModelProperty(
            value = "Product Category Id",
            example = "7ba2a960-2354-466f-8868-6ad713742407",
            dataType = "UUID"
    )
    private UUID idCategory;

    @ApiModelProperty(
            value = "Product Images",
            dataType = "List"
    )
    private List<ProductImageResponse> images = new ArrayList<>();

    @ApiModelProperty(
            value = "Product name",
            example = "hamburger",
            dataType = "String"
    )
    private String name;

    @ApiModelProperty(
            value = "Product description",
            example = "hamburger",
            dataType = "String"
    )
    private String description;

    @ApiModelProperty(
            value = "Product price",
            example = "20.55",
            dataType = "BigDecimal"
    )
    private BigDecimal price;


}
