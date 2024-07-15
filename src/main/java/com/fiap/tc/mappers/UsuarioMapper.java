package com.fiap.tc.mappers;

import org.mapstruct.Mapper;

import com.fiap.tc.domains.seguranca.Usuario;
import com.fiap.tc.dto.UsuarioDto;
import com.fiap.tc.mappers.base.MapperEntity;

@Mapper
public interface UsuarioMapper extends MapperEntity<Usuario, UsuarioDto> {
	
	@Override
	UsuarioDto fromEntity(Usuario usuario);
}
