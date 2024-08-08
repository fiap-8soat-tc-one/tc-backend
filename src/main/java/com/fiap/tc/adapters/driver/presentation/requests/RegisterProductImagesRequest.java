package com.fiap.tc.adapters.driver.presentation.requests;

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
    private UUID idProduct;

    @NotNull
    @Size(min = 1, max = 5)
    @NotEmpty
    @Valid
    private List<ProductImageRequest> images;

}
