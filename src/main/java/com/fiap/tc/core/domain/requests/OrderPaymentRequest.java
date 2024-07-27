package com.fiap.tc.core.domain.requests;

import br.com.caelum.stella.bean.validation.CPF;
import com.fiap.tc.core.domain.model.enums.PaymentType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class OrderPaymentRequest {
    @NotEmpty
    @ApiModelProperty(
            value = "Payment card number",
            example = "4111111111111111",
            dataType = "String",
            required = true
    )
    @Size(max = 20, message = "Invalid card number")
    private String cardNumber;

    @NotEmpty
    @CPF(message = "Invalid card document")
    @ApiModelProperty(
            value = "Payment card document",
            example = "65750888053",
            dataType = "string",
            required = true
    )
    @Size(max = 30, message = "Invalid card document")
    private String cardDocument;

    @NotEmpty
    @ApiModelProperty(
            value = "Payment card print name",
            example = "LUCAS SILVA JUNIOR",
            dataType = "string",
            required = true
    )
    @Size(max = 255, message = "Invalid card name")
    private String cardPrintName;

    @NotEmpty
    @ApiModelProperty(
            value = "Payment card cvc",
            example = "123",
            dataType = "string",
            required = true
    )
    @Size(min = 3, message = "Invalid card cvc number")
    private String cardCvc;

    @NotEmpty
    @ApiModelProperty(
            value = "Payment card expire date mm/yy",
            example = "10/30",
            dataType = "string",
            required = true
    )
    @Size(min = 5, message = "Invalid card expire date")
    private String cardExpireDate;

    @NotNull
    @ApiModelProperty(
            value = "Payment type",
            example = "CREDIT",
            dataType = "enum",
            required = true
    )
    private PaymentType paymentType;
}
