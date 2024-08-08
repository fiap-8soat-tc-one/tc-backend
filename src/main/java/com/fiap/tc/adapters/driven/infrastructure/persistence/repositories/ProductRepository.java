package com.fiap.tc.adapters.driven.infrastructure.persistence.repositories;

import com.fiap.tc.adapters.driven.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    Optional<ProductEntity> findByUuid(UUID uuid);

    Optional<ProductEntity> findByName(String name);

    Page<ProductEntity> findByCategoryUuid(UUID uuidCategory, Pageable pageable);
}
