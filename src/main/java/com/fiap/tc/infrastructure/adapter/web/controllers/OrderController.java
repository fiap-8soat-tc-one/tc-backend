package com.fiap.tc.infrastructure.adapter.web.controllers;

import com.fiap.tc.infrastructure.adapter.web.URLMapping;
import com.fiap.tc.infrastructure.adapter.web.response.DefaultResponse;
import com.fiap.tc.infrastructure.adapter.web.requests.OrderRequest;
import com.fiap.tc.infrastructure.adapter.web.requests.OrderStatusRequest;
import com.fiap.tc.infrastructure.adapter.web.response.OrderListResponse;
import com.fiap.tc.infrastructure.adapter.web.response.OrderResponse;
import com.fiap.tc.core.domain.model.OrderList;
import com.fiap.tc.application.port.in.order.ListOrdersReadyPreparingInputPort;
import com.fiap.tc.application.port.in.order.LoadOrderInputPort;
import com.fiap.tc.application.port.in.order.RegisterOrderInputPort;
import com.fiap.tc.application.port.in.order.UpdateStatusOrderInputPort;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping
@Api(tags = "Orders API V1", produces = APPLICATION_JSON_VALUE)
public class OrderController {

    private final RegisterOrderInputPort registerOrderInputPort;
    private final LoadOrderInputPort loadOrderInputPort;
    private final UpdateStatusOrderInputPort updateStatusOrderInputPort;
    private final ListOrdersReadyPreparingInputPort listOrdersReadyPreparingInputPort;

    public OrderController(RegisterOrderInputPort registerOrderInputPort,
                           LoadOrderInputPort loadOrderInputPort,
                           UpdateStatusOrderInputPort updateStatusOrderInputPort,
                           ListOrdersReadyPreparingInputPort listOrdersReadyPreparingInputPort) {
        this.registerOrderInputPort = registerOrderInputPort;
        this.loadOrderInputPort = loadOrderInputPort;
        this.updateStatusOrderInputPort = updateStatusOrderInputPort;
        this.listOrdersReadyPreparingInputPort = listOrdersReadyPreparingInputPort;
    }

    @ApiOperation(value = "Find Order")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Find Order", response = OrderResponse.class)
    })
    @GetMapping(path = URLMapping.ROOT_PRIVATE_API_ORDERS + "/{id}")
    @PreAuthorize("hasAuthority('SEARCH_ORDERS')")
    public ResponseEntity<OrderResponse> get(
            @ApiParam(required = true, value = "Authorization: Bearer <TOKEN>") @RequestHeader(value = "Authorization") String authorization,
            @PathVariable UUID id) {
        return ok(loadOrderInputPort.load(id));
    }

    @ApiOperation(value = "create order", notes = "(Public Endpoint) This endpoint is responsible for creating the order, receiving the product identifiers and their quantities.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully registered order", response = OrderResponse.class),
    })
    @PostMapping(path = URLMapping.ROOT_PUBLIC_API_ORDERS, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> register(
            @ApiParam(value = "Order details for creating a new order", required = true) @RequestBody @Valid OrderRequest request) {
        return ok(registerOrderInputPort.register(request));
    }

    @ApiOperation(value = "update order status", notes = "(Private Endpoint) This endpoint is responsible for updating the order status for tracking by both the kitchen and the customer (reflected on the system monitor).")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated order status", response = DefaultResponse.class),
            @ApiResponse(code = 401, message = "You are not authorized to perform this action"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @PutMapping(path = URLMapping.ROOT_PRIVATE_API_ORDERS + "/status", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('UPDATE_STATUS_ORDERS')")
    public ResponseEntity<DefaultResponse> updateStatus(
            @ApiParam(value = "Order status update details", required = true) @RequestBody @Valid OrderStatusRequest request) {
        updateStatusOrderInputPort.update(request);
        return ok(new DefaultResponse());
    }

    @ApiOperation(value = "list of orders", notes = "(Private Endpoint) This endpoint is responsible for listing all orders.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of orders", response = OrderListResponse.class, responseContainer = "Page"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @GetMapping(path = URLMapping.ROOT_PRIVATE_API_ORDERS)
    @PreAuthorize("hasAuthority('LIST_ORDERS')")
    public ResponseEntity<Page<OrderList>> list(
            @ApiParam(required = true, value = "Orders Pagination") Pageable pageable) {
        return ok(listOrdersReadyPreparingInputPort.list(pageable));
    }
}
