package com.fiap.tc.common.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.fiap.tc.adapter.repository.entity.core.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fiap.tc.adapter.repository.UsuarioRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findByLogin(login);
		UsuarioEntity usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
		return new UsuarioSistema(usuario, getPermissoes(usuario));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(UsuarioEntity usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		Collection<String> roles = usuarioRepository.getRoles(usuario.getId());
		roles.forEach(perm -> authorities.add(new SimpleGrantedAuthority(perm)));
		return authorities;
	}

}
