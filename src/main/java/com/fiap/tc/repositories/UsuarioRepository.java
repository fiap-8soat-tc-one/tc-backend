package com.fiap.tc.repositories;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fiap.tc.domains.seguranca.Usuario;

@Repository

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Optional<Usuario> findByUuid(UUID uuid);

	public Optional<Usuario> findByLogin(String login);

	public Optional<Usuario> findByEmail(String email);
	
	public Optional<Usuario> findByNumeroDocumento(String numeroDocumento);

	@Query(value = "SELECT p.id FROM seguranca.usuario u JOIN seguranca.usuario_perfil up ON u.id = up.id_usuario JOIN seguranca.perfil pa ON up.id_perfil = pa.id JOIN seguranca.perfil_permissao pp ON up.id_perfil = pp.id_perfil    JOIN seguranca.permissao p ON pp.id_permissao = p.id WHERE u.id = :id ORDER BY p.id", nativeQuery = true)
	Collection<String> getRoles(Long id);

}
