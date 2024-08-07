package com.fiap.tc.infrastructure.adapter.persistence.repositories;

import com.fiap.tc.infrastructure.adapter.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

    Optional<CustomerEntity> findByDocument(String document);

    Optional<CustomerEntity> findByUuid(UUID uuid);
}
