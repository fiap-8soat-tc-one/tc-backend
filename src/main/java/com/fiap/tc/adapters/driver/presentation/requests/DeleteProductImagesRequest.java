package com.fiap.tc.adapters.driver.presentation.requests;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Data
public class DeleteProductImagesRequest {

    @NotNull
    @ApiModelProperty(
            value = "Product Id",
            required = true,
            example = "7ba2a960-2354-466f-8868-6ad713742407",
            dataType = "UUID"
    )
    private UUID idProduct;

    @NotNull
    @Size(min = 1)
    @Valid
    @ApiModelProperty(
            value = "Product Images ids to be deleted",
            required = true,
            dataType = "List<UUID>"
    )
    private List<UUID> images;

}
