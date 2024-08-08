package com.fiap.tc.adapters.repository;

import com.fiap.tc.adapters.repository.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    Optional<CategoryEntity> findByName(String name);

    Optional<CategoryEntity> findByUuid(UUID uuid);

}
