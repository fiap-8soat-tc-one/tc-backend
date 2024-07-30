package com.fiap.tc.adapter.web;

import com.fiap.tc.adapter.web.response.DefaultResponse;
import com.fiap.tc.core.domain.requests.OrderPaymentRequest;
import com.fiap.tc.core.port.in.payment.RegisterPaymentInputPort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Webhook Register Payment")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Process Payment", response = DefaultResponse.class)
    })
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<DefaultResponse> register(
            @RequestBody @Valid OrderPaymentRequest request) {
        registerPaymentInputPort.register(request);
        return ok(new DefaultResponse());
    }


}
