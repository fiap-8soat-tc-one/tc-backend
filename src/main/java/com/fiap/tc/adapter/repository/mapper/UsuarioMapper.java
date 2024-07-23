package com.fiap.tc.adapter.repository.mapper;

import com.fiap.tc.adapter.repository.entity.core.UsuarioEntity;
import org.mapstruct.Mapper;

import com.fiap.tc.core.domain.model.Usuario;
import com.fiap.tc.adapter.repository.mapper.base.MapperEntity;

@Mapper
public interface UsuarioMapper extends MapperEntity<UsuarioEntity, Usuario> {
	
	@Override
	Usuario fromEntity(UsuarioEntity usuario);
}
