package com.fiap.tc.core.port.out.order;

import com.fiap.tc.core.domain.model.Order;
import com.fiap.tc.core.domain.model.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ListOrdersReadyPreparingOutputPort {
    Page<Order> list(List<OrderStatus> status, Pageable pageable);
}

