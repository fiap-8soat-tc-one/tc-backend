package com.fiap.tc.infrastructure.adapter.web.requests;

import br.com.caelum.stella.bean.validation.CPF;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
public class CustomerRequest {

    @NotEmpty
    @CPF(message = "Invalid document")
    @ApiModelProperty(
            value = "CustomerDocument",
            example = "65750888053",
            dataType = "String"
    )
    private String document;

    @Size(max = 255, message = "Invalid name")
    @ApiModelProperty(
            value = "CustomerName",
            required = true,
            example = "Silva",
            dataType = "String"
    )
    private String name;

    @Email(message = "Invalid e-mail")
    @ApiModelProperty(
            value = "CustomerE-mail",
            required = true,
            example = "lucas.silva@gmail.com",
            dataType = "String"
    )
    private String email;
}
