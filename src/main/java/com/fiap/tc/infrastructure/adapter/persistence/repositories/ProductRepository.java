package com.fiap.tc.infrastructure.adapter.persistence.repositories;

import com.fiap.tc.infrastructure.adapter.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    Optional<ProductEntity> findByUuid(UUID uuid);
}
