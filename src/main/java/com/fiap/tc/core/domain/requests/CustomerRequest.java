package com.fiap.tc.core.domain.requests;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CustomerRequest {

    @NotEmpty
    @ApiModelProperty(
            value = "CustomerDocument",
            example = "65750888053",
            dataType = "String"
    )
    private String document;

    @ApiModelProperty(
            value = "CustomerName",
            required = true,
            example = "Silva",
            dataType = "String"
    )
    private String name;

    @ApiModelProperty(
            value = "CustomerE-mail",
            required = true,
            example = "lucas.silva@gmail.com",
            dataType = "String"
    )
    private String email;
}
