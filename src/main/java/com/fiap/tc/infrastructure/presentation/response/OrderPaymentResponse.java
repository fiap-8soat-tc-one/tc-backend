package com.fiap.tc.infrastructure.presentation.response;

import com.fiap.tc.infrastructure.presentation.dtos.PaymentHistoricDto;
import com.fiap.tc.domain.enums.PaymentStatus;
import com.fiap.tc.domain.enums.PaymentType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderPaymentResponse {

    @ApiModelProperty(
            value = "Order Id",
            example = "7ba2a960-2354-466f-8868-6ad713742407",
            dataType = "UUID"
    )
    private UUID id;

    @ApiModelProperty(
            value = "Payment Status",
            example = "APPROVED",
            dataType = "String",
            allowableValues = "APPROVED, REFUSED, ERROR"
    )
    private PaymentStatus status;

    @ApiModelProperty(
            value = "Payment Type",
            example = "CREDIT",
            dataType = "String",
            allowableValues = "CREDIT, DEBIT, PIX"
    )
    private PaymentType paymentType;

    @ApiModelProperty(
            value = "Order Payment Status historic transitions",
            dataType = "List<PaymentHistoricDto>"
    )
    private List<PaymentHistoricDto> paymentHistoric;

    @ApiModelProperty(
            value = "Payment Gateway message response",
            dataType = "String",
            example = "transaction confirmed"
    )
    private String transactionMessage;


}
