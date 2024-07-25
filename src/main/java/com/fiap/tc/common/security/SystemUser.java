package com.fiap.tc.common.security;

import com.fiap.tc.adapter.repository.entity.core.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class SystemUser extends User {

    private static final long serialVersionUID = 1L;

    private UserEntity user;

    public SystemUser(UserEntity user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getLogin(), user.getPassword(), authorities);
        this.user = user;
    }

    public UserEntity getUser() {
        return user;
    }

}
