package com.fiap.tc.core.domain.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Customer {
    @ApiModelProperty(
            value = "Customer Document",
            example = "65750888053",
            dataType = "String"
    )
    private String document;

    @ApiModelProperty(
            value = "Customer name",
            required = true,
            example = "Lucas Silva e Silva",
            dataType = "String"
    )
    private String name;

    @ApiModelProperty(
            value = "Customer E-mail",
            required = true,
            example = "lucas.silva@gmail.com",
            dataType = "String"
    )
    private String email;
}
