package com.fiap.tc.mapper;

import org.mapstruct.Mapper;

import com.fiap.tc.domains.seguranca.Usuario;
import com.fiap.tc.dto.UsuarioDto;
import com.fiap.tc.mapper.base.MapperEntity;

@Mapper
public interface UsuarioMapper extends MapperEntity<Usuario, UsuarioDto> {
	
	@Override
	UsuarioDto fromEntity(Usuario usuario);
}
