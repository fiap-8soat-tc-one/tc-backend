package com.fiap.tc.infrastructure.adapter.repository.mapper;

import com.fiap.tc.infrastructure.adapter.repository.entity.security.UserEntity;
import com.fiap.tc.infrastructure.adapter.repository.mapper.base.MapperEntity;
import com.fiap.tc.core.domain.model.UserSystem;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends MapperEntity<UserEntity, UserSystem> {

    @Override
    UserSystem fromEntity(UserEntity user);
}
