package com.fiap.tc.core.domain.requests;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class RegisterProductImagesRequest {

    @NotNull
    @Size(min = 1, max = 5)
    @NotEmpty
    @Valid
    private List<ProductImageRequest> images;

}
