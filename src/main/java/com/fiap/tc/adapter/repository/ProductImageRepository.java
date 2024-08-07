package com.fiap.tc.adapter.repository;

import com.fiap.tc.adapter.repository.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImageEntity, Integer> {

    Optional<ProductImageEntity> findByUuid(UUID uuid);

    List<ProductImageEntity> findByProductUuid(UUID idProduct);
}
