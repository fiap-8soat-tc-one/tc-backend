package com.fiap.tc.core.application.port.in.order;

import com.fiap.tc.core.domain.response.OrderListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListOrdersReadyPreparingInputPort {
    Page<OrderListResponse> list(Pageable pageable);
}
