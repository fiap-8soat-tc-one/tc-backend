package com.fiap.tc.infrastructure.persistence.repositories;

import com.fiap.tc.infrastructure.persistence.entities.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    Optional<OrderEntity> findByUuid(UUID uuid);

    @Query(value = "SELECT * FROM order_request WHERE status IN :status ORDER BY CASE WHEN status = 'READY' THEN 1 " +
            "WHEN status = 'PREPARING' THEN 2 WHEN status = 'RECEIVED' THEN 3 END, updated_date", nativeQuery = true)
    Page<OrderEntity> findByStatus(@Param("status") List<String> status, Pageable pageable);

    @Query(value = "SELECT NEXTVAL('order_request_id_seq')", nativeQuery = true)
    Integer getNextSequenceValue();
}
