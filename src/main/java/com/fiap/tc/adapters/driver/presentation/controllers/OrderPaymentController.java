package com.fiap.tc.adapters.driver.presentation.controllers;

import com.fiap.tc.adapters.driver.presentation.URLMapping;
import com.fiap.tc.adapters.driver.presentation.requests.OrderPaymentRequest;
import com.fiap.tc.adapters.driver.presentation.response.DefaultResponse;
import com.fiap.tc.adapters.driver.presentation.response.OrderPaymentResponse;
import com.fiap.tc.core.application.ports.in.payment.LoadPaymentInputPort;
import com.fiap.tc.core.application.ports.in.payment.RegisterPaymentInputPort;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static com.fiap.tc.adapters.driver.presentation.mappers.base.MapperConstants.PAYMENT_RESPONSE_MAPPER;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Api(tags = "Orders Payments API V1", produces = APPLICATION_JSON_VALUE)
public class OrderPaymentController {

    private final RegisterPaymentInputPort registerPaymentInputPort;
    private final LoadPaymentInputPort loadPaymentInputPort;

    public OrderPaymentController(RegisterPaymentInputPort registerPaymentInputPort, LoadPaymentInputPort loadPaymentInputPort) {
        this.registerPaymentInputPort = registerPaymentInputPort;
        this.loadPaymentInputPort = loadPaymentInputPort;
    }

    @ApiOperation(value = "Webhook Register Order Payment", notes = "(Public Endpoint) This endpoint is responsible " +
            "for receiving the payment parameters in the selection interface and processing the payment.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully processed payment", response = DefaultResponse.class),
            @ApiResponse(code = 400, message = "Invalid request"),
    })
    @PostMapping(path = URLMapping.ROOT_PUBLIC_API_PAYMENTS, produces = APPLICATION_JSON_VALUE, consumes =
            APPLICATION_JSON_VALUE)
    public ResponseEntity<DefaultResponse> register(
            @ApiParam(value = "find payment status", required = true) @RequestBody @Valid OrderPaymentRequest request) {
        registerPaymentInputPort.register(request.getTransactionNumber(), request.getTransactionMessage(), request.getTransactionDocument(), request.getStatus(), request.getPaymentType(), request.getTotal());
        return ok(new DefaultResponse());
    }

    @ApiOperation(value = "Find Payment Status by OrderId", notes = "(Private Endpoint) This endpoint is responsible " +
            "for search the payment status.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully payment loaded", response =
                    DefaultResponse.class),
            @ApiResponse(code = 400, message = "Invalid request"),
    })
    @PreAuthorize("hasAuthority('SEARCH_ORDERS')")
    @GetMapping(path = URLMapping.ROOT_PRIVATE_API_PAYMENTS, produces = APPLICATION_JSON_VALUE, consumes =
            APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderPaymentResponse> get(@PathVariable UUID id) {
        return ok(PAYMENT_RESPONSE_MAPPER.fromDomain(loadPaymentInputPort.load(id)));
    }
}