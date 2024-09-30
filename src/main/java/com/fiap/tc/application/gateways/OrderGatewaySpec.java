package com.fiap.tc.application.gateways;

import com.fiap.tc.domain.entities.Order;
import com.fiap.tc.domain.entities.OrderItem;
import com.fiap.tc.domain.entities.OrderList;
import com.fiap.tc.domain.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface OrderGatewaySpec {

    Order load(UUID id);

    Order register(UUID idCustomer, List<OrderItem> listOfItems);

    Page<OrderList> list(List<String> status, Pageable pageable);

    void updateStatus(UUID idOrder, OrderStatus status);

}
