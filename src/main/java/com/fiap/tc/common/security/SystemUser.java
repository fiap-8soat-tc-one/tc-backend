package com.fiap.tc.common.security;

import com.fiap.tc.adapters.repository.entity.security.UserEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class SystemUser extends User {

    private static final long serialVersionUID = 1L;

    private final UserEntity user;

    public SystemUser(UserEntity user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getLogin(), user.getPassword(), authorities);
        this.user = user;
    }

}
