package com.fiap.tc.repositories;

import com.fiap.tc.domains.products.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByNameOrDescription(String name, String description);
    Category findByUuid(UUID uuid);

}
