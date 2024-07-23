package com.fiap.tc.common.security;

import com.fiap.tc.adapter.repository.entity.core.UsuarioEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UsuarioSistema extends User {

    private static final long serialVersionUID = 1L;

    private UsuarioEntity usuario;

    public UsuarioSistema(UsuarioEntity usuario, Collection<? extends GrantedAuthority> authorities) {
        super(usuario.getLogin(), usuario.getSenha(), authorities);
        this.usuario = usuario;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

}
