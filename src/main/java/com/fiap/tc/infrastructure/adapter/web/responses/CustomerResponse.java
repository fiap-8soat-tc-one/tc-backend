package com.fiap.tc.infrastructure.adapter.web.requests.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class CustomerResponse {

    @ApiModelProperty(
            value = "Customer Id",
            example = "7ba2a960-2354-466f-8868-6ad713742407",
            dataType = "UUID"
    )
    private UUID id;

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
