package com.fiap.tc.adapter.repository.mapper;

import com.fiap.tc.adapter.repository.entity.core.UsuarioEntity;
import com.fiap.tc.adapter.repository.mapper.base.MapperEntity;
import com.fiap.tc.core.domain.model.Usuario;
import org.mapstruct.Mapper;

@Mapper
public interface UsuarioMapper extends MapperEntity<UsuarioEntity, Usuario> {

    @Override
    Usuario fromEntity(UsuarioEntity usuario);
}
