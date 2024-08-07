package com.fiap.tc.infrastructure.adapter.web.controllers;

import com.fiap.tc.infrastructure.adapter.web.URLMapping;
import com.fiap.tc.infrastructure.adapter.web.response.DefaultResponse;
import com.fiap.tc.infrastructure.adapter.web.requests.OrderPaymentRequest;
import com.fiap.tc.application.port.in.payment.RegisterPaymentInputPort;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = URLMapping.ROOT_PUBLIC_API_PAYMENT)
@Api(tags = "Orders Payment Hook API V1", produces = APPLICATION_JSON_VALUE)
public class PaymentHookController {

    private final RegisterPaymentInputPort registerPaymentInputPort;

    public PaymentHookController(RegisterPaymentInputPort registerPaymentInputPort) {
        this.registerPaymentInputPort = registerPaymentInputPort;
    }

    @ApiOperation(value = "Webhook Register Payment", notes = "(Public Endpoint) This endpoint is responsible for receiving the payment parameters in the selection interface and processing the payment.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully processed payment", response = DefaultResponse.class),
            @ApiResponse(code = 400, message = "Invalid request"),
    })
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<DefaultResponse> register(
            @ApiParam(value = "Order payment request details", required = true) @RequestBody @Valid OrderPaymentRequest request) {
        registerPaymentInputPort.register(request);
        return ok(new DefaultResponse());
    }
}