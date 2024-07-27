package com.fiap.tc.adapter.repository;

import com.fiap.tc.adapter.repository.entity.core.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByLogin(String login);

    @Query(nativeQuery = true, value = "SELECT p.id FROM seguranca.usuario u JOIN seguranca.usuario_perfil up ON u.id = up.id_usuario JOIN seguranca.perfil pa ON up.id_perfil = pa.id JOIN seguranca.perfil_permissao pp ON up.id_perfil = pp.id_perfil JOIN seguranca.permissao p ON pp.id_permissao = p.id WHERE u.id = :id ORDER BY p.id")
    Collection<String> getRoles(Long id);

}
