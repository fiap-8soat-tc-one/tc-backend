package com.fiap.tc.core.application.ports.out.order;

import com.fiap.tc.core.domain.fixed.OrderStatus;
import com.fiap.tc.adapters.driver.presentation.response.OrderListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ListOrdersReadyPreparingOutputPort {
    Page<OrderListResponse> list(List<OrderStatus> status, Pageable pageable);
}

