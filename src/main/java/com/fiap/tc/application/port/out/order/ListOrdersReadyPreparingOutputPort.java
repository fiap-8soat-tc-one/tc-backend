package com.fiap.tc.application.port.out.order;

import com.fiap.tc.core.domain.enums.OrderStatus;
import com.fiap.tc.core.domain.model.OrderList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ListOrdersReadyPreparingOutputPort {
    Page<OrderList> list(List<OrderStatus> status, Pageable pageable);
}

