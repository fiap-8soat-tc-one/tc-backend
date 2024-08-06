package com.fiap.tc.core.domain.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class ProductImage {

    @ApiModelProperty(
            value = "Product Image Id",
            example = "7ba2a960-2354-466f-8868-6ad713742407",
            dataType = "UUID"
    )
    private UUID id;

    @ApiModelProperty(
            value = "Product Image name",
            example = "hamburger front",
            dataType = "String"
    )
    private String name;

    @ApiModelProperty(
            value = "Product Image description",
            example = "hamburger front",
            dataType = "String"
    )
    private String description;


    @ApiModelProperty(
            value = "Product Image base64 encoded",
            dataType = "String"
    )
    private String image;

}
