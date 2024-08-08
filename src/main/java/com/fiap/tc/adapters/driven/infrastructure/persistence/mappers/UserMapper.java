package com.fiap.tc.adapters.driven.infrastructure.persistence.mappers;

import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.security.UserEntity;
import com.fiap.tc.adapters.driven.infrastructure.persistence.mappers.base.MapperEntity;
import com.fiap.tc.core.domain.model.security.UserSystem;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends MapperEntity<UserEntity, UserSystem> {

    @Override
    UserSystem fromEntity(UserEntity user);
}
