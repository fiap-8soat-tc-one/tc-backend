package com.fiap.tc.infrastructure.presentation.requests;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class ProductImageRequest {

    @NotEmpty
    @Size(max = 255, message = "Invalid product image name")
    @ApiModelProperty(
            value = "Product image name",
            required = true,
            example = "hamburger",
            dataType = "String"
    )
    private String name;

    @Size(max = 255, message = "Invalid product image description")
    @ApiModelProperty(
            value = "Product description",
            example = "hamburger 200g",
            dataType = "String"
    )
    private String description;

    @NotEmpty
    @ApiModelProperty(
            value = "Product Image base64 encoded",
            dataType = "String",
            required = true
    )
    private String image;

}
