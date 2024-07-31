package com.fiap.tc.adapter.web;

import com.fiap.tc.adapter.repository.entity.CategoryEntity;
import com.fiap.tc.core.domain.model.Order;
import com.fiap.tc.core.domain.requests.OrderRequest;
import com.fiap.tc.core.domain.requests.OrderStatusRequest;
import com.fiap.tc.core.domain.response.OrderResponse;
import com.fiap.tc.core.port.in.order.ListOrdersReadyPreparingInputPort;
import com.fiap.tc.core.port.in.order.LoadOrderInputPort;
import com.fiap.tc.core.port.in.order.RegisterOrderInputPort;
import com.fiap.tc.core.port.in.order.UpdateStatusOrderInputPort;
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

    @ApiOperation(value = "Register Customer Orders")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Save Category", response = OrderResponse.class)
    })
    @PostMapping(path = URLMapping.ROOT_PUBLIC_API_ORDERS, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> register(
            @RequestBody @Valid OrderRequest request) {
        return ok(registerOrderInputPort.register(request));
    }

    @ApiOperation(value = "Update Order Status")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update Order Status", response = Order.class)
    })
    @PutMapping(path = URLMapping.ROOT_PRIVATE_API_ORDERS + "/status", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('UPDATE_STATUS_ORDERS')")
    public ResponseEntity<Order> updateStatus(
            @RequestBody @Valid OrderStatusRequest request) {
        return ok(updateStatusOrderInputPort.update(request));
    }

    @ApiOperation(value = "List Orders Preparing or Ready")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List Orders Preparing or Ready", response = CategoryEntity.class)
    })
    @GetMapping(path = URLMapping.ROOT_PRIVATE_API_ORDERS)
    @PreAuthorize("hasAuthority('LIST_ORDERS')")
    public ResponseEntity<Page<Order>> list(
            @ApiParam(required = true, value = "Authorization: Bearer <TOKEN>") @RequestHeader(value = "Authorization") String authorization,
            @ApiParam(required = true, value = "Orders Pagination") Pageable pageable) {
        return ok(listOrdersReadyPreparingInputPort.list(pageable));
    }


}
