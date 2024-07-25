package com.fiap.tc.adapter.repository;

import com.fiap.tc.adapter.repository.entity.core.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public Optional<UserEntity> findByUuid(UUID uuid);

    public Optional<UserEntity> findByLogin(String login);

    public Optional<UserEntity> findByEmail(String email);

    public Optional<UserEntity> findByDocumentNumber(String documentNumber);

    @Query(nativeQuery = true, value = "${sql.query.getRoles}")
    Collection<String> getRoles(Long id);

}
