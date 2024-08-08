package com.fiap.tc.adapters.driver.presentation.requests;

import br.com.caelum.stella.bean.validation.CPF;
import com.fiap.tc.core.domain.enums.PaymentResult;
import com.fiap.tc.core.domain.enums.PaymentType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class OrderPaymentRequest {

    @NotNull
    @ApiModelProperty(
            value = "Transaction Number",
            example = "7ba2a960-2354-466f-8868-6ad713742407",
            dataType = "String"
    )
    private String transactionNumber;

    @NotNull
    @ApiModelProperty(
            value = "Transaction Message Result - Order Id",
            example = "transaction successfully",
            dataType = "String"
    )
    @Size(max = 255, message = "Invalid Transaction Message Result")
    private String transactionMessage;

    @NotEmpty
    @CPF(message = "Invalid document")
    @ApiModelProperty(
            value = "Document",
            example = "65750888053",
            dataType = "string",
            required = true
    )
    @Size(max = 30, message = "Invalid Transaction Document")
    private String transactionDocument;

    @NotNull
    @ApiModelProperty(
            value = "Transaction Result",
            example = "SUCCESS",
            dataType = "enum",
            required = true,
            allowableValues = "SUCCESS, ERROR"
    )
    private PaymentResult result;

    @NotNull
    @ApiModelProperty(
            value = "Payment type",
            example = "CREDIT",
            dataType = "enum",
            required = true,
            allowableValues = "CREDIT, DEBIT, PIX"
    )
    private PaymentType paymentType;

    @NotNull
    @ApiModelProperty(
            value = "Amount paid",
            example = "100.50",
            dataType = "BigDecimal",
            required = true
    )
    private BigDecimal total;

}
