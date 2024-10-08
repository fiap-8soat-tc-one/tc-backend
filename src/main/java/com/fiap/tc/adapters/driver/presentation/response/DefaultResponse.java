package com.fiap.tc.adapters.driver.presentation.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DefaultResponse {

    @ApiModelProperty(
            value = "Response status",
            example = "SUCCESS"
    )
    protected String status = "SUCCESS";
    @ApiModelProperty(
            value = "Response messages",
            example = "[\"Operation successfully completed\"]"
    )
    private List<String> messages = new ArrayList<>();

    public void setMessage(String message) {
        messages.add(message);
    }

    public void setMessages(List<String> errorMessages) {
        messages.addAll(errorMessages);
    }
}
