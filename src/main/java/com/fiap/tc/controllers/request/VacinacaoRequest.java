package com.fiap.tc.controllers.request;

import static com.fiap.tc.commons.constants.ValidationConstants.ONLY_DIGITS;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import br.com.caelum.stella.bean.validation.CPF;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VacinacaoRequest {
	
	@NotEmpty
	@CPF(message = "Não é um CPF válido")
	@Pattern(regexp = ONLY_DIGITS, message="Campo CPF deve conter apenas números")
	@ApiModelProperty(
	        value = "Cpf do Cliente",
	        required = true,
	        example = "95772929097",
	        dataType = "String"
    )
	private String cpf;
}
