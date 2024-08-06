package com.fiap.tc.core.domain.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class UpdateProductNameRequest {
    @NotNull
    private UUID idProduct;
    @NotNull
    private String name;
}
