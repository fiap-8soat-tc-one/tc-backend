package com.fiap.tc.infrastructure.adapter.persistence.mapper;

import com.fiap.tc.infrastructure.adapter.persistence.entity.security.UserEntity;
import com.fiap.tc.infrastructure.adapter.persistence.mapper.base.MapperEntity;
import com.fiap.tc.core.domain.model.UserSystem;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends MapperEntity<UserEntity, UserSystem> {

    @Override
    UserSystem fromEntity(UserEntity user);
}
