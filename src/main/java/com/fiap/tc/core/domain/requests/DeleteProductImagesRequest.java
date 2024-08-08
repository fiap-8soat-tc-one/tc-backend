package com.fiap.tc.core.domain.requests;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Data
public class DeleteProductImagesRequest {

    @NotNull
    private UUID idProduct;

    @NotNull
    @Size(min = 1)
    @Valid
    private List<UUID> images;

}
