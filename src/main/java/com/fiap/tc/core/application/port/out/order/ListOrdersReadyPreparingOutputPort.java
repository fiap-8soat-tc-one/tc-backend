package com.fiap.tc.core.port.out.order;

import com.fiap.tc.core.domain.model.enums.OrderStatus;
import com.fiap.tc.core.domain.response.OrderListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ListOrdersReadyPreparingOutputPort {
    Page<OrderListResponse> list(List<OrderStatus> status, Pageable pageable);
}

