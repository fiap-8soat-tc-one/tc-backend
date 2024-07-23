package com.fiap.tc.adapter.web.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DefaultResponse {

    @ApiModelProperty(
            value = "Status da resposta",
            example = "SUCCESS"
    )
    protected String status = "SUCCESS";
    @ApiModelProperty(
            value = "Mensagens da resposta",
            example = "[\"Sucesso ao cadastrar usuário\"]"
    )
    private List<String> messages = new ArrayList<>();

    public void setMessage(String message) {
        messages.add(message);
    }
}
