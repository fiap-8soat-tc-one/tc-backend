package com.fiap.tc.adapter.repository.mapper;

import com.fiap.tc.adapter.repository.entity.core.UserEntity;
import com.fiap.tc.adapter.repository.mapper.base.MapperEntity;
import com.fiap.tc.core.domain.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UsuarioMapper extends MapperEntity<UserEntity, User> {

    @Override
    User fromEntity(UserEntity user);
}
