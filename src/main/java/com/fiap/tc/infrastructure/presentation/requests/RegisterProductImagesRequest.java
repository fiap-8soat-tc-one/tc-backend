package com.fiap.tc.infrastructure.presentation.requests;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Data
public class RegisterProductImagesRequest {

    @NotNull
    @ApiModelProperty(
            value = "Product Id",
            example = "7ba2a960-2354-466f-8868-6ad713742407",
            dataType = "UUID",
            required = true
    )
    private UUID idProduct;

    @NotNull
    @Size(min = 1, max = 5)
    @NotEmpty
    @Valid
    @ApiModelProperty(
            value = "List of Product Images to be uploaded",
            required = true,
            dataType = "List<ProductImageRequest>"
    )
    private List<ProductImageRequest> images;

}
