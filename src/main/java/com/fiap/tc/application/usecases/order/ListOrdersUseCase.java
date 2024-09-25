package com.fiap.tc.application.usecases.order;

import com.fiap.tc.application.gateways.OrderGatewaySpec;
import com.fiap.tc.domain.entities.OrderList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fiap.tc.domain.enums.OrderStatus.*;

@Service
public class ListOrdersUseCase {

    private final OrderGatewaySpec orderGateway;

    public ListOrdersUseCase(OrderGatewaySpec orderGateway) {
        this.orderGateway = orderGateway;
    }

    public Page<OrderList> list(Pageable pageable) {
        return orderGateway.list(List.of(READY.name(), PREPARING.name(), RECEIVED.name()),
                pageable);
    }
}


