package com.fiap.tc.adapters.driven.infrastructure.persistence.repositories;

import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.security.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByLogin(String login);

    @Query(nativeQuery = true, value = "select p.id from security.user_system u join security.user_profile up on u.id = up.id_user join security.profile pf on up.id_profile = pf.id join security.roles_profile rp on up.id_profile = rp.id_profile join security.role p on rp.id_role = p.id where u.id = :id order by p.id")
    Collection<String> getRoles(Long id);

}
