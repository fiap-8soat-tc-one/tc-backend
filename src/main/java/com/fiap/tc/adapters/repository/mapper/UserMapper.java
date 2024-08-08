package com.fiap.tc.adapters.repository.mapper;

import com.fiap.tc.adapters.repository.entity.security.UserEntity;
import com.fiap.tc.adapters.repository.mapper.base.MapperEntity;
import com.fiap.tc.core.domain.model.security.UserSystem;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends MapperEntity<UserEntity, UserSystem> {

    @Override
    UserSystem fromEntity(UserEntity user);
}
