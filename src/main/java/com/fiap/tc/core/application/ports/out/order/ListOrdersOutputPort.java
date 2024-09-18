package com.fiap.tc.core.application.ports.out.order;

import com.fiap.tc.core.domain.entities.OrderList;
import com.fiap.tc.core.domain.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ListOrdersOutputPort {
    Page<OrderList> list(List<String> status, Pageable pageable);
}

