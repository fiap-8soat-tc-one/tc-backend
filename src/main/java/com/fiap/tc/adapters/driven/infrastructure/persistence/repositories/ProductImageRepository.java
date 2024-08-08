package com.fiap.tc.adapters.driven.infrastructure.persistence.repositories;

import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImageEntity, Integer> {

}
