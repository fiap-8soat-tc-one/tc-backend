package com.fiap.tc.adapters.driven.infrastructure.persistence.repositories;

import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.OrderEntity;
import com.fiap.tc.core.domain.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    Optional<OrderEntity> findByUuid(UUID uuid);

    Page<OrderEntity> findByStatusIn(List<OrderStatus> status, Pageable pageable);
}
