package com.fiap.tc.adapter.repository;

import com.fiap.tc.adapter.repository.entity.OrderPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderPaymentRepository extends JpaRepository<OrderPaymentEntity, Integer> {

    OrderPaymentEntity findByUuid(UUID uuid);

    OrderPaymentEntity findByOrderUuid(UUID orderUuid);
}
