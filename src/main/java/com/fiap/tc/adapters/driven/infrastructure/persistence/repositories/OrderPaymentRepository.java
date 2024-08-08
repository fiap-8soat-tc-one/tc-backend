package com.fiap.tc.adapters.driven.infrastructure.persistence.repositories;

import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.OrderPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderPaymentRepository extends JpaRepository<OrderPaymentEntity, Integer> {

    Optional<OrderPaymentEntity> findByUuid(UUID uuid);

    Optional<OrderPaymentEntity> findByOrderUuid(UUID orderUuid);
}
