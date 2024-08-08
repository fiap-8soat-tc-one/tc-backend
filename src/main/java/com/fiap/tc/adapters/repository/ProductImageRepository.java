package com.fiap.tc.adapters.repository;

import com.fiap.tc.adapters.repository.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImageEntity, Integer> {

}
